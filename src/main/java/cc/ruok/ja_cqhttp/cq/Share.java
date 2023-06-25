package cc.ruok.ja_cqhttp.cq;

public class Share {

    public static String url(String url, String title, String content, String image) {
        return "[CQ:share,url=" + url + ",title=" + title + ",content=" + content + ",image=" + image + "]";
    }

    public static String url(String url, String title, String content) {
        return "[CQ:share,url=" + url + ",title=" + title + ",content=" + content + "]";
    }

    public static String url(String url, String title) {
        return "[CQ:share,url=" + url + ",title=" + title + "]";
    }

    public static String url(String url) {
        return "[CQ:share,url=" + url + "";
    }

}
