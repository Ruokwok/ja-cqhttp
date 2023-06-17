package cc.ruok.ja_cqhttp;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WSClient extends WebSocketClient {

    private OneBotClient obc;

    protected WSClient(URI serverUri, OneBotClient obc) {
        super(serverUri);
        this.obc = obc;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
    }

    @Override
    public void onMessage(String s) {
//        System.out.println(s);
        if (!s.contains("heartbeat")) System.out.println(s);
        obc.callEvent(s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
    }

    @Override
    public void onError(Exception e) {
    }
}
