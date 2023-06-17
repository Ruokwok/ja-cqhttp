package cc.ruok.ja_cqhttp.events;

public class FriendRecallEvent extends NoticeEvent {

    protected long message_id;

    public long getFriendId() {
        return user_id;
    }

    public long getMessageId() {
        return message_id;
    }

}
