package cc.ruok.ja_cqhttp.events;

public class GruopNoticeEvent extends Event {

    protected String notice_type;
    protected long group_id;
    protected long user_id;
    protected String sub_type;

    public String getNoticeType() {
        if (sub_type == null) return notice_type;
        if (sub_type.equals("set")) return notice_type + "_set";
        if (sub_type.equals("unset")) return notice_type + "_unset";
        if (sub_type.equals("ban")) return notice_type + "_ban";
        if (sub_type.equals("lift_ban")) return notice_type + "_unban";
        if (notice_type.equals("notify")) return sub_type;
        return notice_type;
    }

    /**
     * @return 获取群号
     */
    public long getGroupId() {
        return group_id;
    }

}
