package cc.ruok.ja_cqhttp;

import java.net.URI;

public class OneBotClient extends OneBot {

    protected WSClient wsc;
    private boolean running = false;

    /**
     * 创建OneBot客户端
     *
     * @param address   OneBot Server地址
     */
    public OneBotClient(String address) {
        super(null);
        this.wsc = new WSClient(URI.create(address), this);
        this.ws = wsc;
    }

    public Thread runAsync(long reconnect) {
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

    public Thread runAsync() {
        return runAsync(5000);
    }

    public void run() {
        if (running) return;
        running = true;
        wsc.run();
        running = false;
    }

    @Override
    public void close() {
        super.close();
    }

}
