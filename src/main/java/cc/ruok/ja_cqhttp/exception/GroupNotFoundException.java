package cc.ruok.ja_cqhttp.exception;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(long group) {
        super("群聊不存在: " + group);
    }

}
