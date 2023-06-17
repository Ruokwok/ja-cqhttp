package cc.ruok.ja_cqhttp.events;

public class GruopNoticeEvent extends Event {

    protected String notice_type;
    protected long group_id;
    protected long user_id;
    protected String sub_type;

    public String getNoticeType() {
        if (sub_type.equals("set")) return notice_type + "_set";
        if (sub_type.equals("unset")) return notice_type + "_unset";
        return notice_type;
    }

    /**
     * @return 获取群号
     */
    public long getGroupId() {
        return group_id;
    }

}
