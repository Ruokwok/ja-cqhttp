package cc.ruok.ja_cqhttp.exception;

public class TimeoutException extends RuntimeException {

    public TimeoutException() {
        super("操作超时，请检查与OneBot实现端连接状态.");
    }

}
