package cc.ruok.ja_cqhttp.events;

public class GroupAdminSetEvent extends GruopNoticeEvent {

    protected String sub_type;

    public long getAdminId() {
        return user_id;
    }

}
