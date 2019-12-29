package com.yuanshenbin.basic.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2018/1/24
 * desc   :
 */

public class JsonUtils {
    private static Gson gson = new Gson();

    /**
     * json管理
     */
    public static String string(Object object) {
        return gson.toJson(object);
    }

    public static <T> T object(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T object(String json, Type type) {
        return gson.fromJson(json, type);


    }

    public static <T> List<T> jsonToList(String json) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        List<T> list = new Gson().fromJson(json, type);
        return list;
    }

}
