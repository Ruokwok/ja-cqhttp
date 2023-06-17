package cc.ruok.ja_cqhttp.events;

public class GroupMessageEvent extends MessageEvent {

    protected Anonymous anonymous;
    protected GruopSender sender;

    /**
     * @return 是否为匿名消息
     */
    public boolean isAnonymous() {
        return sub_type.equals("anonymous");
    }

    public Anonymous getAnonymous() {
        return anonymous;
    }

    public GruopSender getSender() {
        return sender;
    }

}
