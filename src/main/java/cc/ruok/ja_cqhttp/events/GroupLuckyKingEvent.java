package cc.ruok.ja_cqhttp.events;

public class GroupLuckyKingEvent extends NoticeEvent {

    protected long target_id;

    /**
     * @return 红包运气王ID
     */
    public long getLuckyKingId() {
        return target_id;
    }

    /**
     * @return 红包发送者ID
     */
    public long getSenderId() {
        return user_id;
    }

}
