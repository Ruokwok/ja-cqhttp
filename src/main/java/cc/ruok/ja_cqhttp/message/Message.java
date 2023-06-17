package cc.ruok.ja_cqhttp.message;

public class Message {

    private String msg;
    private boolean escape;

    public Message(String msg, boolean escape) {
        this.msg = msg;
        this.escape = escape;
    }

    public Message(String msg) {
        this(msg, false);
    }

}
