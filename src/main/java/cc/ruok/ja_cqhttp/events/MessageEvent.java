package cc.ruok.ja_cqhttp.events;

public class MessageEvent extends Event {

    protected String message_type;
    protected String sub_type;
    protected int message_id;
    protected long user_id;
    protected String message;
    protected String raw_message;
    protected int font;

    public String getMessageType() {
        return message_type;
    }

    public String getSubType() {
        return sub_type;
    }

    public int getMessageId() {
        return message_id;
    }

    public String getMessageString() {
        return message;
    }

    public String getRawMessage() {
        return raw_message;
    }

    public long getSenderQQ() {
        return user_id;
    }

    public int getFont() {
        return font;
    }

}
