package cc.ruok.ja_cqhttp.api;

import cc.ruok.ja_cqhttp.events.GroupInviteEvent;

public class GroupMessageAPI extends API {

    public GroupMessageAPI(long id, String message, boolean escape) {
        action = "send_group_msg";
        params.put("group_id", id);
        params.put("message", message);
        params.put("auto_escape", escape);
    }

}
