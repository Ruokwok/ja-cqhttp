package cc.ruok.ja_cqhttp.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("群聊或用户不存在");
    }

}
