package cc.ruok.ja_cqhttp.events;

public class GroupDecreaseEvent extends NoticeEvent {

    protected long operator_id;

    /**
     * @return 获取操作者
     */
    public long getOperatorId() {
        return operator_id;
    }

    /**
     * @return 获取离开者
     */
    public long getLeaver() {
        return user_id;
    }

    /**
     * @return 是否为被踢
     */
    public boolean isKicked() {
        return sub_type.equals("kick");
    }

    /**
     * @return 是否为主动退出
     */
    public boolean isQuit() {
        return sub_type.equals("leave");
    }

}
