package cc.ruok.ja_cqhttp.exception;

import cc.ruok.ja_cqhttp.OneBot;

public class NotSupportedException extends RuntimeException {

    public NotSupportedException(OneBot bot, String api) {
        super("当前OneBot实现端(" + bot.getAppName() + ")不支持此API: " + api);
    }

}
