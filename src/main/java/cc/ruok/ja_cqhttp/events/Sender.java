package cc.ruok.ja_cqhttp.events;

public class Sender {

    protected long user_id;
    protected String nickname;
    protected String sex;
    protected int age;

    public long getId() {
        return user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public String sex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

}
