package cc.ruok.ja_cqhttp.api;

public class SetGroupNameAPI extends API {

    public SetGroupNameAPI(long group, String name) {
        action = "set_group_name";
        params.put("group_id", group);
        params.put("group_name", name);
    }

}
