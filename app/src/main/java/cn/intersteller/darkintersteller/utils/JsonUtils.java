package cn.intersteller.darkintersteller.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Description : Json转换工具类
 */
public class JsonUtils {

    private static Gson mGson;

    static {
        mGson = new GsonBuilder().setPrettyPrinting().create();
    }

    //JSON转换为对象1--普通类型
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return mGson.fromJson(json, classOfT);
    }

    //JSON转换为对象-针对泛型的类型
    public static <T> T fromJson(String json, Type typeOfT) {
        return mGson.fromJson(json, typeOfT);
    }

    //对象转换为json
    public static String toJson(Object object) {
        return mGson.toJson(object);
    }

}
