package cc.ruok.ja_cqhttp.events;

public class GroupAdminSetEvent extends GruopNoticeEvent {

    public long getAdminId() {
        return user_id;
    }

}
