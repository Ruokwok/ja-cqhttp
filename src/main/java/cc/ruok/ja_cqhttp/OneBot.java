package cc.ruok.ja_cqhttp;

import cc.ruok.ja_cqhttp.api.GroupMessageAPI;
import cc.ruok.ja_cqhttp.api.PrivateMessageAPI;
import cc.ruok.ja_cqhttp.events.*;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OneBot {

    protected HashMap<EventListener, LinkedList<Method>> listeners = new HashMap<>();
    protected static HashMap<String, Class<?>> types = new HashMap<>();
    protected WebSocket ws;

    static {
        types.put("heartbeat", HeartbeatEvent.class);
        types.put("group", GroupMessageEvent.class);
        types.put("private", PrivateMessageEvent.class);
        types.put("group_upload", GroupFileUploadEvent.class);
        types.put("group_admin_set", GroupAdminSetEvent.class);
        types.put("group_admin_unset", GroupAdminUnsetEvent.class);
        types.put("group_decrease", GroupDecreaseEvent.class);
        types.put("group_increase", GroupJoinEvent.class);
        types.put("group_ban_ban", GroupBanEvent.class);
        types.put("group_ban_unban", GroupUnbanEvent.class);
        types.put("group_recall", GroupRecallEvent.class);
        types.put("friend_recall", FriendRecallEvent.class);
        types.put("poke", GroupPokeEvent.class);
        types.put("lucky_king", GroupLuckyKingEvent.class);
        types.put("honor", GroupHonorEvent.class);
        types.put("friend_add", FriendAddEvent.class);
        types.put("request_friend", FriendRequestEvent.class);
        types.put("request_add_group", GroupRequestEvent.class);
        types.put("request_invite_group", GroupInviteEvent.class);
    }

    public OneBot(WebSocket ws) {
        this.ws = ws;
    }

    public void registerListener(EventListener listener) {
        LinkedList<Method> list = new LinkedList<>();
        Method[] methods = listener.getClass().getDeclaredMethods();
        for (Method method: methods) {
            if (method.isAnnotationPresent(Handler.class) && method.getParameterCount() == 1) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (Event.class.isAssignableFrom(parameterTypes[0])) {
                    list.add(method);
                }
            }
        }
        listeners.put(listener, list);
    }

    public void unregisterListener(EventListener listener) {
        listeners.remove(listener);
    }

    protected void callEvent(Event event) {
        for (Map.Entry<EventListener, LinkedList<Method>> entry: listeners.entrySet()) {
            for (Method method: entry.getValue()) {
                if (method.getParameters()[0].getType().getName().equals(event.getClass().getName())) {
                    try {
                        method.invoke(entry.getKey(), event);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected void callEvent(String json) {
        Gson gson = new Gson();
        Event event = gson.fromJson(json, Event.class);
        if (event.getPostType().equals("meta_event")) {
            MetaEvent metaEvent = gson.fromJson(json, MetaEvent.class);
            callEvent(metaEvent);
            Class<?> aClass = types.get(metaEvent.getMetaType());
            if (aClass != null) callEvent((Event) gson.fromJson(json, aClass));
        } else if (event.getPostType().equals("message")) {
            MessageEvent messageEvent = gson.fromJson(json, MessageEvent.class);
            Class<?> aClass = types.get(messageEvent.getMessageType());
            if (aClass != null) callEvent((Event) gson.fromJson(json, aClass));
        } else if (event.getPostType().equals("notice")) {
            NoticeEvent noticeEvent = gson.fromJson(json, NoticeEvent.class);
            Class<?> aClass = types.get(noticeEvent.getNoticeType());
            if (aClass != null) callEvent((Event) gson.fromJson(json, aClass));
        } else if (event.getPostType().equals("request")) {
            RequestEvent requestEvent = gson.fromJson(json, RequestEvent.class);
            Class<?> aClass = types.get(requestEvent.getRequestType());
            if (aClass != null) callEvent((Event) gson.fromJson(json, aClass));
        }
    }


    public void sendJson(String json) {
        ws.send(json);
    }

    public void sendPrivateMessage(long id, String message, boolean escape) {
        ws.send(new PrivateMessageAPI(id, message, escape).toString());
    }

    public void sendGroupMessage(long group, String message, boolean escape) {
        ws.send(new GroupMessageAPI(group, message, escape).toString());
    }

}
