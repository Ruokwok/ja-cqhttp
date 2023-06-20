package cc.ruok.ja_cqhttp.api;

public class SetGroupUserTitleAPI extends API {

    public SetGroupUserTitleAPI(long group, long user, String title) {
        action = "set_group_special_title";
        params.put("group_id", group);
        params.put("user_id", user);
        params.put("special_title", title);
        params.put("duration", -1);
    }

}
