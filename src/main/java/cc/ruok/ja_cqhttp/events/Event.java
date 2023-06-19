package cc.ruok.ja_cqhttp.events;

import cc.ruok.ja_cqhttp.OneBot;

public class Event {

    public long time;
    public long self_id;
    public String post_type;
    private OneBot bot;
    private String json;

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

    public void setOneBot(OneBot bot) {
        this.bot = bot;
    }

    public OneBot getOneBot() {
        return bot;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
