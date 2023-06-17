package cc.ruok.ja_cqhttp;

public class OneBotServer extends OneBot {

    protected WSServer wss;

    public OneBotServer(int port) {
        wss = new WSServer(port);
    }

    public Thread start() {
        Thread t = new Thread(wss::start);
        t.start();
        return t;
    }

    public void stop() throws InterruptedException {
        wss.stop();
    }

}
