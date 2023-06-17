package cc.ruok.ja_cqhttp.events;

public class GroupAdminUnsetEvent extends NoticeEvent {

    public long getAdminId() {
        return user_id;
    }
}
