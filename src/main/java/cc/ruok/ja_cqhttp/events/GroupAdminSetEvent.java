package cc.ruok.ja_cqhttp.events;

public class GroupAdminSetEvent extends NoticeEvent {

    public long getAdminId() {
        return user_id;
    }

}
