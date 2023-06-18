package cc.ruok.ja_cqhttp.events;

import cc.ruok.ja_cqhttp.Message;

public class GroupMessageSendEvent extends Event {

    protected Message message;

    public GroupMessageSendEvent(Message message) {
        this.message = message;
        this.self_id = message.getSelf();
    }

    public Message getMessage() {
        return message;
    }

    public String getEcho() {
        return message.getEcho();
    }

    public long getMessageId() {
        return message.getMessageId();
    }

}
