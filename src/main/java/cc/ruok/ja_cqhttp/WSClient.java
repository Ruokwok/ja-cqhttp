package cc.ruok.ja_cqhttp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WSClient extends WebSocketClient {

    private OneBot bot;

    protected WSClient(URI serverUri, OneBot bot) {
        super(serverUri);
        this.bot = bot;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
    }

    @Override
    public void onMessage(String s) {
//        System.out.println(s);
        if (!s.contains("heartbeat")) System.out.println(s);
//        bot.callEvent(s);
        if (s.contains("echo")) bot.callEvent(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
    }

    @Override
    public void onError(Exception e) {
    }
}
