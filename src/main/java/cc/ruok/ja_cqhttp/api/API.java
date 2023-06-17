package cc.ruok.ja_cqhttp.api;

import com.google.gson.Gson;

import java.util.HashMap;

public class API {

    public String action;
    public HashMap<String, Object> params = new HashMap<>();

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
