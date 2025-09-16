package cc.ruok.ja_cqhttp.events;

public class WebSocketCloseEvent extends Event {

    private int code;
    private String msg;

    public WebSocketCloseEvent(int code, String msg, boolean remote) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

}
