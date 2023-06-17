package cc.ruok.ja_cqhttp.events;

public class FirendRecallEvent extends Event {

    protected String notice_type;
    protected long user_id;
    protected long message_id;

    public long getFriendId() {
        return user_id;
    }

    public long getMessageId() {
        return message_id;
    }

}
