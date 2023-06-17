package cc.ruok.ja_cqhttp.api;

public class PrivateMessageAPI extends API {

    public PrivateMessageAPI(long id, String message, boolean escape) {
        this.action = "send_private_msg";
        params.put("user_id", id);
        params.put("message", message);
        params.put("auto_escape", escape);
    }

}
