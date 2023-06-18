package cc.ruok.ja_cqhttp.events;

public class GroupMessageSendEvent extends Event {

    protected String echo;
    protected String message;
    protected String status;
    protected int retcode;
    protected Data data;

    public String getEcho() {
        return echo;
    }

    public long getMessageId() {
        return data.message_id;
    }

    public class Data {
        protected long message_id;
    }

}
