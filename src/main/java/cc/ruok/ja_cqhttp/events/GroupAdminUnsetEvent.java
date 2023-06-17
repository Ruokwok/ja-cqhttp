package cc.ruok.ja_cqhttp.events;

public class GroupAdminUnsetEvent extends GruopNoticeEvent {

    public long getAdminId() {
        return user_id;
    }
}
