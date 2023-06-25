package cc.ruok.ja_cqhttp;

import cc.ruok.ja_cqhttp.api.MessageAPI;

public class Message {

    protected long time;
    private MessageAPI msg;
    private String content;
    private boolean isGroup;
    private long self;
    private long messageId;
    private long senderId;
    private long groupId;

    protected Message(MessageAPI msg, String content, boolean isGroup, long self, long sender, long group) {
        this.msg = msg;
        this.content = content;
        this.isGroup = isGroup;
        this.time = System.currentTimeMillis();
        this.self = self;
        this.senderId = sender;
        this.groupId = group;
    }

    protected Message(String content, boolean isGroup, long self, long sender, long group) {
        this(null, content, isGroup, self, sender, group);
    }

    public boolean isGroup() {
        return isGroup;
    }

    protected void setMessageAPI(MessageAPI msg) {
        this.msg = msg;
    }

    protected void setMessageId(long id) {
        this.messageId = id;
    }

    public String getEcho() {
        return msg.echo;
    }

    public long getMessageId() {
        try {
            return msg.data.message_id;
        } catch (Exception e) {
            return messageId;
        }
    }

    public String getString() {
        return content;
    }

    public String getFormatString() {
        return content.replaceAll("\\[CQ:face[^\\]]*\\]", "[face]")
                .replaceAll("\\[CQ:image[^\\]]*\\]", "[image]")
                .replaceAll("\\[CQ:dice[^\\]]*\\]", "[dice]")
                .replaceAll("\\[CQ:shake[^\\]]*\\]", "[shake]")
                .replaceAll("\\[CQ:poke[^\\]]*\\]", "[poke]")
                .replaceAll("\\[CQ:share[^\\]]*\\]", "[share]")
                .replaceAll("\\[CQ:contact[^\\]]*\\]", "[contact]")
                .replaceAll("\\[CQ:location[^\\]]*\\]", "[location]")
                .replaceAll("\\[CQ:music[^\\]]*\\]", "[music]")
                .replaceAll("\\[CQ:xml[^\\]]*\\]", "[xml]")
                .replaceAll("\\[CQ:json[^\\]]*\\]", "[json]")
                .replaceAll("\\[CQ:rps[^\\]]*\\]", "[rps]");
    }

    public long getTime() {
        return time;
    }

    public long getSelf() {
        return self;
    }

    public void recall() {
        OneBot bot = OneBot.getActiveInstance(self);
        if (bot == null) {
            //TODO 没有活跃的OneBot实例时
        } else {
            bot.recallMessage(getMessageId());
        }
    }

    public void reply(String message) {
        OneBot bot = OneBot.getActiveInstance(self);
        if (bot == null) {
        } else {
            bot.replyGroupMessage(groupId, messageId, message);
        }
    }
}
