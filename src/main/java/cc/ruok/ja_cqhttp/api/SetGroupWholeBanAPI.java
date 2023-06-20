package cc.ruok.ja_cqhttp.api;

public class SetGroupWholeBanAPI extends API {

    public SetGroupWholeBanAPI(long group, boolean enable) {
        action = "set_group_whole_ban";
        params.put("group_id", group);
        params.put("enable", enable);
    }

}
