package cc.ruok.ja_cqhttp;

import cc.ruok.ja_cqhttp.api.*;
import cc.ruok.ja_cqhttp.exception.NotFoundException;
import cc.ruok.ja_cqhttp.exception.NotSupportedException;
import cc.ruok.ja_cqhttp.exception.PermissionDeniedException;
import cc.ruok.ja_cqhttp.exception.TimeoutException;
import cc.ruok.ja_cqhttp.events.*;
import com.google.gson.Gson;
import org.java_websocket.WebSocket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OneBot {

    protected static HashMap<Long, OneBot> active = new HashMap<>();

    protected HashMap<EventListener, LinkedList<Method>> listeners = new HashMap<>();
    protected HashMap<String, API> sync = new HashMap<>();
    protected static HashMap<String, Class<?>> types = new HashMap<>();
    protected WebSocket ws;
    private long self;
    private String appName;
    private String appVersion;
    private String protocol;

    private HashMap<String, Message> msg = new HashMap<>();

    static {
        types.put("heartbeat", HeartbeatEvent.class);
        types.put("lifecycle", LifecycleEvent.class);
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

    public static OneBot getActiveInstance(long id) {
        return active.get(id);
    }

    public OneBot(WebSocket ws) {
        this.ws = ws;
        OneBot bot = this;
        registerListener(new EventListener() {
            @Handler
            public void onLifecycle(LifecycleEvent event) {
                self = event.getSelf();
                active.put(self, bot);
            }
        });
    }

    public void close() {
        ws.close();
        active.remove(self);
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
        event.setOneBot(this);
        if (event instanceof GroupMessageEvent) {
            GroupMessageEvent ge = (GroupMessageEvent) event;
            Message message = new Message(ge.getMessageString(), true, self);
            message.setMessageId(ge.getMessageId());
            ge.setMessage(message);
        }
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
        Class<?> aClass = null;
        event.setJson(json);
        callEvent(event);
        if (event.getPostType() == null) {
            Response response = gson.fromJson(json, Response.class);
            response.json = json;
            onResponse(response);
        } else if (event.getPostType().equals("meta_event")) {
            MetaEvent metaEvent = gson.fromJson(json, MetaEvent.class);
            callEvent(metaEvent);
            aClass = types.get(metaEvent.getMetaType());
        } else if (event.getPostType().equals("message")) {
            MessageEvent messageEvent = gson.fromJson(json, MessageEvent.class);
            aClass = types.get(messageEvent.getMessageType());
        } else if (event.getPostType().equals("notice")) {
            NoticeEvent noticeEvent = gson.fromJson(json, NoticeEvent.class);
            aClass = types.get(noticeEvent.getNoticeType());
        } else if (event.getPostType().equals("request")) {
            RequestEvent requestEvent = gson.fromJson(json, RequestEvent.class);
            aClass = types.get(requestEvent.getRequestType());
        }
        if (aClass != null) {
            Event e = (Event) gson.fromJson(json, aClass);
            e.setJson(json);
            callEvent(e);
        }
    }

    private void onResponse(Response response) {
        Gson gson = new Gson();
        if (this.msg.containsKey(response.echo)) {
            this.msg.get(response.echo).setMessageAPI(gson.fromJson(response.json, MessageAPI.class));
            if (this.msg.get(response.echo).isGroup()) {
                GroupMessageSendEvent sendEvent = new GroupMessageSendEvent(this.msg.get(response.echo));
                callEvent(sendEvent);
            } else {
                PrivateMessageSendEvent sendEvent = new PrivateMessageSendEvent(this.msg.get(response.echo));
                callEvent(sendEvent);
            }
            this.msg.remove(response.echo);
        } else if (response.echo.equals("get_version_info")) {
            appName = response.data.app_name;
            appVersion = response.data.app_version;
            protocol = response.data.protocol_version;
        } else {
            API api = sync.get(response.echo);
            api.data = response.data;
            api.code = response.retcode;
            api.msg = response.msg;
            synchronized (api) {
                api.notify();
            }
        }
    }

    private <T extends API> T waitResponse(T api, int wait) {
        sync.put(api.getEcho(), api);
        synchronized (api) {
            try {
                api.wait(wait);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        sync.remove(api.getEcho());
        if (api.code == -1) throw new TimeoutException();
        switch (api.msg) {
            case "API_NOT_FOUND": throw new NotSupportedException(this, api.action);
            case "GROUP_NOT_FOUND": throw new NotFoundException();
            case "NOT_MANAGEABLE": throw new PermissionDeniedException();
        }
        return api;
    }

    private <T extends API> T waitResponse(T api) {
        return waitResponse(api, 2000);
    }

    public long getSelf() {
        return self;
    }

    public void sendJson(String json) {
        ws.send(json);
    }

    public void sendPrivateMessage(long group, String message, boolean escape, String echo) {
        PrivateMessageAPI msg = new PrivateMessageAPI(group, message, echo, escape);
        this.msg.put(msg.getEcho(), new Message(message, false, self));
        ws.send(msg.toString());
    }

    public void sendPrivateMessage(long group, String message, boolean escape) {
        sendPrivateMessage(group, message, escape, null);
    }

    public void sendPrivateMessage(long group, String message) {
        sendPrivateMessage(group, message, false);
    }

    public void sendGroupMessage(long group, String message, boolean escape, String echo) {
        GroupMessageAPI msg = new GroupMessageAPI(group, message, echo, escape);
        this.msg.put(msg.getEcho(), new Message(message, true, self));
        ws.send(msg.toString());
    }

    public void sendGroupMessage(long group, String message, boolean escape) {
        sendGroupMessage(group, message, escape, null);
    }

    public void sendGroupMessage(long group, String message) {
        sendGroupMessage(group, message, false);
    }

    public void recallMessage(long messageId) {
        RecallMessageAPI recall = new RecallMessageAPI(messageId);
        ws.send(recall.toString());
    }

    public void recallMessage(Message message) {
        recallMessage(message.getMessageId());
    }

    public void sendLike(long id, int count) {
        int times = count > 10 ? 10 : Math.max(count, 1);
        SendLikeAPI api = new SendLikeAPI(id, times);
        ws.send(api.toString());
        waitResponse(api);
    }

    public void sendLike(long id) {
        sendLike(id, 1);
    }

    public Message getMessage(long id) {
        GetMessageAPI api = new GetMessageAPI(id);
        ws.send(api.toString());
        api = waitResponse(api);
        return new Message(api.data.message, api.data.group, self);
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public String getProtocol() {
        return protocol;
    }

    /**
     * 禁言群成员
     * @param group 群号
     * @param id 目标id
     * @param time 禁言时长 单位秒
     */
    public void setGroupBan(long group, long id, int time) {
        SetGroupBanAPI api = new SetGroupBanAPI(group, id, time);
        sendJson(api.toString());
        waitResponse(api);
    }

    public void setGroupBan(long group, long id) {
        setGroupBan(group, id, 600);
    }

    public void liftGroupBan(long group, long id) {
        setGroupBan(group, id, 0);
    }

    public void setGroupWholeBan(long group) {
        SetGroupWholeBanAPI api = new SetGroupWholeBanAPI(group, true);
        sendJson(api.toString());
        waitResponse(api);
    }

    public void liftGroupWholeBan(long group) {
        SetGroupWholeBanAPI api = new SetGroupWholeBanAPI(group, false);
        sendJson(api.toString());
        waitResponse(api);
    }

    public void kickFromGroup(long group, long user, boolean reject) {
        SetGroupKickAPI api = new SetGroupKickAPI(group, user, reject);
        sendJson(api.toString());
        waitResponse(api);
    }
}
