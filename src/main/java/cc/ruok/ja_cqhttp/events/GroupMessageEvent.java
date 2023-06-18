package cc.ruok.ja_cqhttp.events;

import cc.ruok.ja_cqhttp.Message;

public class GroupMessageEvent extends MessageEvent {

    protected Anonymous anonymous;
    protected GruopSender sender;

    protected Message message_obj;

    /**
     * @return 是否为匿名消息
     */
    public boolean isAnonymous() {
        return sub_type.equals("anonymous");
    }

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public GruopSender getSender() {
        return sender;
    }

    public void setMessage(Message message) {
        this.message_obj = message;
    }

    public Message getMessage() {
        return message_obj;
    }

}
