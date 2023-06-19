package cc.ruok.ja_cqhttp.exception;

public class TimeoutException extends RuntimeException {

    public TimeoutException() {
        super("获取返回结果超时，请检查与OneBot实现端连接状态.");
    }

}
