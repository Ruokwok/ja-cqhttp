package cc.ruok.ja_cqhttp;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class WSServer extends WebSocketServer {

    private OneBotServer server;

    public WSServer(OneBotServer server, int port) {
        super(new InetSocketAddress(port));
        this.server = server;
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake clientHandshake) {
        OneBot bot = new OneBot(ws);
        server.bots.put(ws, bot);
        for (EventListener listener : server.baseListeners) {
            bot.registerListener(listener);
        }
    }

    @Override
    public void onClose(WebSocket ws, int i, String s, boolean b) {
        server.bots.remove(ws);
    }

    @Override
    public void onMessage(WebSocket ws, String s) {
        OneBot bot = server.bots.get(ws);
        if (bot != null) bot.callEvent(s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
