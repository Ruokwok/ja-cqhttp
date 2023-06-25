package cc.ruok.ja_cqhttp.cq;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class FlashImage {

    public static String url(String url) {
        return "[CQ:image,type:flash,file=" + url + "]";
    }

    public static String file(File file) throws IOException {
        return "[CQ:image,type:flash,file=base64://" + Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath())) + "]";
    }

}
