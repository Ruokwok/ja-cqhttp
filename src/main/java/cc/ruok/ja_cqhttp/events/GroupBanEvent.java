package cc.ruok.ja_cqhttp.events;

public class GroupBanEvent extends NoticeEvent {

    protected long duration;
    protected long operator_id;

    /**
     * @return 获取操作者
     */
    public long getOperatorId() {
        return operator_id;
    }

    /**
     * @return 禁言时长 单位：秒
     */
    public long getDuration() {
        return duration;
    }

    public long getTargetId() {
        return user_id;
    }

}
