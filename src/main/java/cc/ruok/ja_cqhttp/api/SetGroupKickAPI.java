package cc.ruok.ja_cqhttp.api;

public class SetGroupKickAPI extends API {

    public SetGroupKickAPI(long group, long user, boolean reject) {
        action = "set_group_kick";
        params.put("group_id", group);
        params.put("user_id", user);
        params.put("reject_add_request", reject);
    }

}
