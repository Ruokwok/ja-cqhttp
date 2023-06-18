package cc.ruok.ja_cqhttp;

import cc.ruok.ja_cqhttp.api.MessageAPI;

public class Message {

    protected long time;
    private MessageAPI msg;
    private String content;
    private boolean isGroup;

    protected Message(MessageAPI msg, String content, boolean isGroup) {
        this.msg = msg;
        this.content = content;
        this.isGroup = isGroup;
        this.time = System.currentTimeMillis();
    }

    protected Message(String content, boolean isGroup) {
        this(null, content, isGroup);
    }

    public boolean isGroup() {
        return isGroup;
    }

    protected void setMessageAPI(MessageAPI msg) {
        this.msg = msg;
    }

    public String getEcho() {
        return msg.echo;
    }

    public long getMessageId() {
        return msg.data.message_id;
    }

    public String getString() {
        return content;
    }

    public long getTime() {
        return time;
    }
}
