package cc.ruok.ja_cqhttp.api;

public class SetGroupAdminAPI extends API {

    public SetGroupAdminAPI(long group, long user, boolean set) {
        action = "set_group_admin";
        params.put("group_id", group);
        params.put("user_id", user);
        params.put("enable", set);
    }

}
