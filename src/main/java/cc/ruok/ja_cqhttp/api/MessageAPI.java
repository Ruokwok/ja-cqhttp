package cc.ruok.ja_cqhttp.api;

public class MessageAPI {

    public String echo;
    public String message;
    public int retcode;
    public Data data;

    public class Data {
        public long message_id;
    }

}
