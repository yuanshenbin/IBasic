package com.yuanshenbin.basic.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2021-09-21
 * desc   :
 */
public class SpUtils {

    private static MMKV sMMKV;

    public static MMKV getMMKV() {
        if (sMMKV == null) {
            sMMKV = MMKV.defaultMMKV();
        }
        return sMMKV;
    }

    public static void removeValuesForKeys(String... key) {
        getMMKV().removeValuesForKeys(key);
    }

    public static void putInt(String key, int value) {
        getMMKV().encode(key, value);
    }

    public static int getInt(String key) {
        return getMMKV().decodeInt(key);
    }

    public static void putString(String key, String value) {
        getMMKV().encode(key, value);
    }

    public static String getString(String key) {
        return getMMKV().decodeString(key);
    }

    public static void putBoolean(String key, boolean value) {
        getMMKV().encode(key, value);
    }

    public static boolean getBoolean(String key) {
        return getMMKV().decodeBool(key);
    }

    public static void putLong(String key, long value) {
        getMMKV().encode(key, value);
    }

    public static long getLong(String key) {
        return getMMKV().decodeLong(key);
    }

    public static void putFloat(String key, float value) {
        getMMKV().encode(key, value);
    }

    public static float getFloat(String key) {
        return getMMKV().decodeFloat(key);
    }

    public <T> T getObject(String key, Class<T> cls) {
        return JsonUtils.object(getString(key), cls);
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
    public <T> List<T> getList(String key, Class<T> cls) {
        List<T> list = new ArrayList<>();
        String json = getString(key);
        if (!Utils.isEmpty(json)) {
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
