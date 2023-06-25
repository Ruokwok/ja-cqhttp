package cc.ruok.ja_cqhttp.api;

import cc.ruok.ja_cqhttp.Response;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.UUID;

public class API {

    public String action;
    public HashMap<String, Object> params = new HashMap<>();
    public String echo = randomUUID();
    public Response.Data data;
    public int code = -1;
    public String msg;
    public String wording;

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public String getEcho() {
        return echo;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
