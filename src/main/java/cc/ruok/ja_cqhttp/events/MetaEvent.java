package cc.ruok.ja_cqhttp.events;

public class MetaEvent extends Event {

    protected String meta_event_type;

    /**
     * @return 元事件类型
     */
    public String getMetaType() {
        return meta_event_type;
    }
}
