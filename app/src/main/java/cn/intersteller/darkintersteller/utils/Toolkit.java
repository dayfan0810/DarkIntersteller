package cn.intersteller.darkintersteller.utils;

import com.google.gson.Gson;

public class Toolkit {
    private static Gson gson;


    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


}
