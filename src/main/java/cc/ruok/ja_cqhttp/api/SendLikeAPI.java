package cc.ruok.ja_cqhttp.api;

public class SendLikeAPI extends API {

    public SendLikeAPI(long id, int count) {
        action = "send_like";
        params.put("user_id", id);
        params.put("times", count);
    }

}
