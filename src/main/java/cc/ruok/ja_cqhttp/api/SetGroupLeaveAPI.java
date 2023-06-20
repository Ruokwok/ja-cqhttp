package cc.ruok.ja_cqhttp.api;

public class SetGroupLeaveAPI extends API {

    public SetGroupLeaveAPI(long group, boolean dismiss) {
        action = "set_group_name";
        params.put("group_id", group);
        params.put("is_dismiss", dismiss);
    }

}
