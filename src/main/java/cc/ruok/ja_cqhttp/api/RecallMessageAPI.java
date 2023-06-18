package cc.ruok.ja_cqhttp.api;

public class RecallMessageAPI extends API {

    public RecallMessageAPI(long messageId) {
        action = "delete_msg";
        params.put("message_id", messageId);
    }

}
