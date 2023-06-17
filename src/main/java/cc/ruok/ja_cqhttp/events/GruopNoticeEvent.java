package cc.ruok.ja_cqhttp.events;

public class GruopNoticeEvent extends Event {

    protected String notice_type;
    protected long group_id;
    protected long user_id;

    public String getNoticeType() {
        return notice_type;
    }

    /**
     * @return 获取群号
     */
    public long getGroupId() {
        return group_id;
    }

}
