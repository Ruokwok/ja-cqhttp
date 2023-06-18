package cc.ruok.ja_cqhttp;

public class Message {

    protected String echo;
    protected String message;
    protected String status;
    protected int retcode;
    protected Data data = new Data();
    protected long time;

    protected Message(String echo, String message) {
        this.echo = echo;
        this.time = System.currentTimeMillis();
        this.message = message;
    }

    public String getEcho() {
        return echo;
    }

    public long getMessageId() {
        return data.message_id;
    }

    public void setMessageId(long id) {
        this.data = new Data();
        this.data.message_id = id;
    }

    public class Data {
        protected long message_id;
    }

}
