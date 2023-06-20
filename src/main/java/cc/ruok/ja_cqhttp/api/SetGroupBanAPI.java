package cc.ruok.ja_cqhttp.api;

public class SetGroupBanAPI extends API {

    public SetGroupBanAPI(long group, long id, int duration) {
        action = "set_group_ban";
        params.put("group_id", group);
        params.put("user_id", id);
        params.put("duration", duration);
    }

}
