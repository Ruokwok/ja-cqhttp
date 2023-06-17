package cc.ruok.ja_cqhttp;

import cc.ruok.ja_cqhttp.events.*;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OneBot {

    protected HashMap<EventListener, LinkedList<Method>> listeners = new HashMap<>();
    protected static HashMap<String, Class<?>> types = new HashMap<>();

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
            callEvent(messageEvent);
            Class<?> aClass = types.get(messageEvent.getMessageType());
            if (aClass != null) callEvent((Event) gson.fromJson(json, aClass));
        } else if (event.getPostType().equals("notice")) {
            GruopNoticeEvent noticeEvent = gson.fromJson(json, GruopNoticeEvent.class);
            callEvent(noticeEvent);
            Class<?> aClass = types.get(noticeEvent.getNoticeType());
            if (aClass != null) callEvent((Event) gson.fromJson(json, aClass));
        }
    }

}
