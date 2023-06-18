package cc.ruok.ja_cqhttp.api;

public class PrivateMessageAPI extends API {

    public PrivateMessageAPI(long id, String message, String echo, boolean escape) {
        this.echo = echo == null ? randomUUID() : echo;
        this.action = "send_private_msg";
        params.put("user_id", id);
        params.put("message", message);
        params.put("auto_escape", escape);
    }

}
