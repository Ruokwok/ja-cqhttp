package cc.ruok.ja_cqhttp.events;

public class GruopSender extends Sender {

    protected String role;
    protected String title;
    protected String card;

    /**
     * @return 获取群权限
     */
    public String getRole() {
        return role;
    }

    /**
     * @return 获取称号
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return 获取马甲
     */
    public String getCard() {
        return card;
    }

}
