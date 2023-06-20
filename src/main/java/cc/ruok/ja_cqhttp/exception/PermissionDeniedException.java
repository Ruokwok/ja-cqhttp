package cc.ruok.ja_cqhttp.exception;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException() {
        super("机器人权限不足");
    }

}
