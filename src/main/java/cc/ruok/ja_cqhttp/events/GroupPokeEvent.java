package cc.ruok.ja_cqhttp.events;

public class GroupPokeEvent extends NoticeEvent {

    protected long target_id;

    public long getSenderId() {
        return user_id;
    }

    public long getTargetId() {
        return target_id;
    }

}
