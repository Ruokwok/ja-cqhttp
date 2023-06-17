package cc.ruok.ja_cqhttp.events;

public class GroupJoinEvent extends NoticeEvent {

    protected long operator_id;

    /**
     * @return 获取操作者
     */
    public long getOperatorId() {
        return operator_id;
    }

    /**
     * @return 是否为邀请入群
     */
    public boolean isInvite() {
        return sub_type.equals("invite");
    }

    public long getInviter() {
        if (isInvite()) return operator_id;
        return 0;
    }

    public long getApprover() {
        if (sub_type.equals("approve")) return operator_id;
        return 0;
    }

}
