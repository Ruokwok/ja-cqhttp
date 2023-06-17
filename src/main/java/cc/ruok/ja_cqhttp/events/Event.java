package cc.ruok.ja_cqhttp.events;

import com.google.gson.Gson;

public class Event {

    public long time;
    public long self_id;
    public String post_type;

    /**
     * @return 机器人的QQ号
     */
    public long getSelf() {
        return self_id;
    }

    /**
     * @return 消息时间戳
     */
    public long getTime() {
        return time;
    }

    /**
     * @return 事件类型
     */
    public String getPostType() {
        return post_type;
    }
}
