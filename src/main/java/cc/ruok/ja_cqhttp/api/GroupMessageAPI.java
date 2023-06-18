package cc.ruok.ja_cqhttp.api;

public class GroupMessageAPI extends API {

    public GroupMessageAPI(long id, String message, String echo, boolean escape) {
        action = "send_group_msg";
        this.echo = echo == null ? randomUUID() : echo;
        params.put("group_id", id);
        params.put("message", message);
        params.put("auto_escape", escape);
    }

}
