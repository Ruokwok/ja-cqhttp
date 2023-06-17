package cc.ruok.ja_cqhttp.events;

public class GroupRecallEvent extends GruopNoticeEvent {

    protected long message_id;
    protected long operator_id;

    /**
     * @return 获取操作者
     */
    public long getOperatorId() {
        return operator_id;
    }

    /**
     * @return 被撤回的消息ID
     */
    public long getMessageId() {
        return message_id;
    }

}
