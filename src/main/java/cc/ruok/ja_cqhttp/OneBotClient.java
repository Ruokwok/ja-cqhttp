package cc.ruok.ja_cqhttp;

import java.net.URI;

public class OneBotClient extends OneBot {

    protected WSClient wsc;
    private boolean running = false;
    private int reconnect;

    public OneBotClient(String address) {
        this(address, 10000);
    }

    /**
     * 创建OneBot客户端
     *
     * @param address   OneBot Server地址
     * @param reconnect 断线重连时间 <1时为不重连
     */
    public OneBotClient(String address, int reconnect) {
        this.reconnect = reconnect;
        this.wsc = new WSClient(URI.create(address), this);
    }

    public Thread run() {
        if (running) return null;
        running = true;
        Thread t = new Thread(() -> {
            while (reconnect > 0) {
                wsc.run();
                try {
                    if (reconnect > 0) {
                        Thread.sleep(reconnect);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            running = false;
        });
        t.start();
        return t;
    }

    public void close() {
        reconnect = -1;
        wsc.close();
    }

    public void setReconnect(int time) {
        this.reconnect = time;
    }
}
