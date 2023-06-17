package cc.ruok.ja_cqhttp.events;

public class PrivateMessageEvent extends MessageEvent {

    protected Sender sender;

    /**
     * @return 是否为好友消息
     */
    public boolean isFriend() {
        return sub_type.equals("friend");
    }

    /**
     * @return 是否为群临时消息
     */
    public boolean isGroup() {
        return sub_type.equals("group");
    }

    public Sender getSender() {
        return sender;
    }

}
