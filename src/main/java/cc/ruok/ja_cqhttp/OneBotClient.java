package cc.ruok.ja_cqhttp;

import java.net.URI;

public class OneBotClient extends OneBot {

    protected WSClient wsc;
    private boolean running = false;
    private long reconnect;
    private String token;

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

    public void setToken(String token) {
        this.token = token;
        wsc.addHeader("Authorization", "Bearer " + token);
    }

    public Thread runAsync(long reconnect) {
        if (running) return null;
        this.reconnect = reconnect;
        running = true;
        Thread t = new Thread(() -> {
            while (this.reconnect > 0) {
                wsc.run();
                try {
                    if (this.reconnect > 0) {
                        Thread.sleep(this.reconnect);
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
        this.reconnect = 0;
        super.close();
    }

    public boolean isConnecting() {
        return wsc.getConnection().isOpen();
    }

}
