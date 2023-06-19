package cc.ruok.ja_cqhttp.api;

public class GetMessageAPI extends API {

    public GetMessageAPI(long id) {
        action = "get_msg";
        params.put("message_id", id);
    }

}
