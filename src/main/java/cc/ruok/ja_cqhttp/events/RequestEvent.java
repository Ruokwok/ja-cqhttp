package cc.ruok.ja_cqhttp.events;

public class RequestEvent extends Event {

    protected String request_type;
    protected String comment;
    protected String flag;
    protected long user_id;
    protected String sub_type;

    public String getRequestType() {
        if (sub_type.equals("add")) return "request_add_group";
        if (sub_type.equals("invite")) return "request_invite_group";
        return "request_" + request_type;
    }

    public String getComment() {
        return comment;
    }

    public String getFlag() {
        return flag;
    }


    /**
     * @return 申请者ID
     */
    public long getApplicantId() {
        return user_id;
    }

}
