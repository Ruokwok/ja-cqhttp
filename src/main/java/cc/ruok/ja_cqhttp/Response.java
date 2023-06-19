package cc.ruok.ja_cqhttp;

import cc.ruok.ja_cqhttp.events.Sender;

import java.util.HashMap;

public class Response {

    public Data data;
    public String echo;
    public String status;
    public String json;

    public class Data {

        boolean group;
        long group_id;
        String message;
        long message_id;
        String message_id_v2;
        int message_seq;
        String message_type;
        int real_id;
        long time;
        Sender sender;
        String app_name;
        String app_version;
        String protocol_version;
    }

}
