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
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        OneBot bot = new OneBot(webSocket);
        server.bots.put(webSocket, bot);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        server.bots.remove(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        OneBot bot = server.bots.get(webSocket);
        if (bot != null) bot.callEvent(s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
