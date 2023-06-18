package cc.ruok.ja_cqhttp.api;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.UUID;

public class API {

    public String action;
    public HashMap<String, Object> params = new HashMap<>();
    public String echo;

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public String getEcho() {
        return echo;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
