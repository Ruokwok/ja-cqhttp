package cc.ruok.ja_cqhttp;

import org.java_websocket.WebSocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OneBotServer {

    protected WSServer wss;
    protected HashMap<WebSocket, OneBot> bots = new HashMap<>();
    protected ArrayList<EventListener> baseListeners = new ArrayList<>();
    protected HashMap<Long, ArrayList<EventListener>> listeners = new HashMap<>();

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

    public void registerListener(EventListener listener) {
        baseListeners.add(listener);
        for (Map.Entry<WebSocket, OneBot> entry : bots.entrySet()) {
            entry.getValue().registerListener(listener);
        }
    }

    public void unregisterListener(EventListener listener, boolean now) {
        baseListeners.remove(listener);
        if (!now) return;
        for (Map.Entry<WebSocket, OneBot> entry : bots.entrySet()) {
            entry.getValue().unregisterListener(listener);
        }
    }

}
