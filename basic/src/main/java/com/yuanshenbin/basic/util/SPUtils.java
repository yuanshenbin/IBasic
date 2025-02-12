package com.yuanshenbin.basic.util;

import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2021-09-21
 * desc   :
 */
public class SPUtils {
    private static SPProxy instance;

    public static SPProxy initialize(SPProxy proxy) {
        if (instance == null) {
            instance = proxy;
        }
        return instance;
    }

    public static void removeValuesForKeys(String key) {
        instance.getSP(key).removeValueForKey(key);

    }

    public static void putParcelable(String key, Parcelable value) {
        instance.getSP(key).encode(key, value);
    }


    public <T extends Parcelable> T getParcelable(String key, Class<T> value) {
        return instance.getSP(key).decodeParcelable(key, value);
    }

    public static void putInt(String key, int value) {
        instance.getSP(key).encode(key, value);
    }

    public static int getInt(String key) {
        return instance.getSP(key).decodeInt(key);
    }

    public static void putString(String key, String value) {
        instance.getSP(key).encode(key, value);
    }

    public static String getString(String key) {
        return instance.getSP(key).decodeString(key);
    }

    public static void putBoolean(String key, boolean value) {
        instance.getSP(key).encode(key, value);
    }

    public static boolean getBoolean(String key) {
        return instance.getSP(key).decodeBool(key);
    }

    public static void putLong(String key, long value) {
        instance.getSP(key).encode(key, value);
    }

    public static long getLong(String key) {
        return instance.getSP(key).decodeLong(key);
    }

    public static void putFloat(String key, float value) {
        instance.getSP(key).encode(key, value);
    }

    public static float getFloat(String key) {
        return instance.getSP(key).decodeFloat(key);
    }

    public static <T> T getObject(String key, Class<T> cls) {
        try {
            return IJsonUtils.object(getString(key), cls);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取保存的集合
     * 尽量不好非常大的数据，毕竟读取也耗时
     *
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String key, Class<T> cls) {
        List<T> list = new ArrayList<>();
        String json = getString(key);
        if (!IUtils.isEmpty(json)) {
            try {
                Gson gson = new Gson();
                JsonArray array = new JsonParser().parse(json).getAsJsonArray();
                for (JsonElement elem : array) {
                    list.add(gson.fromJson(elem, cls));
                }
            } catch (Exception e) {

            }

        }
        return list;
    }
}
