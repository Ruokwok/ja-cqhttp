package cc.ruok.ja_cqhttp.events;

public class GroupFileUploadEvent extends NoticeEvent {

    protected File file;

    public long getSenderId() {
        return user_id;
    }

    public String getFileId() {
        return file.id;
    }

    public String getFileName() {
        return file.name;
    }

    public long getFileSize() {
        return file.size;
    }

    public long getBusid() {
        return file.busid;
    }

    public String getFileUrl() {
        return file.url;
    }

    public class File {

        protected String id;
        protected String name;
        protected long size;
        protected long busid;
        protected String url;

    }

}
