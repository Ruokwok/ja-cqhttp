package cc.ruok.ja_cqhttp;

import org.java_websocket.WebSocket;

import java.util.HashMap;

public class OneBotServer {

    protected WSServer wss;
    protected HashMap<WebSocket, OneBot> bots = new HashMap<>();

    public OneBotServer(int port) {
        wss = new WSServer(this, port);
    }

    public Thread startAsync() {
        Thread t = new Thread(wss::start);
        t.start();
        return t;
    }

    public void start() {
        wss.start();
    }

    public void stop() throws InterruptedException {
        wss.stop();
    }

}
