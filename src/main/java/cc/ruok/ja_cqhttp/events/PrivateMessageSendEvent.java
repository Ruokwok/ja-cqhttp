package cc.ruok.ja_cqhttp.events;

import cc.ruok.ja_cqhttp.Message;

public class PrivateMessageSendEvent extends GroupMessageSendEvent {
    public PrivateMessageSendEvent(Message message) {
        super(message);
    }
}
