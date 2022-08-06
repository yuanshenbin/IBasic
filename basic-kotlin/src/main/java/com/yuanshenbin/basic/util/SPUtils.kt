package com.yuanshenbin.basic.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.tencent.mmkv.MMKV
import java.util.*

/**
 * author : yuanshenbin
 * time   : 2021-09-21
 * desc   :
 */
object SPUtils {
    private var sMMKV: MMKV? = null
    val mMKV: MMKV?
        get() {
            if (sMMKV == null) {
                sMMKV = MMKV.defaultMMKV()
            }
            return sMMKV
        }
    @JvmStatic
    fun removeValuesForKeys(vararg key: String?) {
        mMKV!!.removeValuesForKeys(key)
    }
    @JvmStatic
    fun putInt(key: String?, value: Int) {
        mMKV!!.encode(key, value)
    }
    @JvmStatic
    fun getInt(key: String?): Int {
        return mMKV!!.decodeInt(key)
    }
    @JvmStatic
    fun putString(key: String?, value: String?) {
        mMKV!!.encode(key, value)
    }
    @JvmStatic
    fun getString(key: String?): String? {
        return mMKV!!.decodeString(key)
    }
    @JvmStatic
    fun putBoolean(key: String?, value: Boolean) {
        mMKV!!.encode(key, value)
    }
    @JvmStatic
    fun getBoolean(key: String?): Boolean {
        return mMKV!!.decodeBool(key)
    }
    @JvmStatic
    fun putLong(key: String?, value: Long) {
        mMKV!!.encode(key, value)
    }
    @JvmStatic
    fun getLong(key: String?): Long {
        return mMKV!!.decodeLong(key)
    }
    @JvmStatic
    fun putFloat(key: String?, value: Float) {
        mMKV!!.encode(key, value)
    }
    @JvmStatic
    fun getFloat(key: String?): Float {
        return mMKV!!.decodeFloat(key)
    }
    @JvmStatic
    fun <T> getObject(key: String?, cls: Class<T>?): T? {
        return try {
            JsonUtils.`object`(getString(key), cls)
        } catch (e: Exception) {
            null
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
    </T> */
    @JvmStatic
    fun <T> getList(key: String?, cls: Class<T>?): List<T> {
        val list: MutableList<T> = ArrayList()
        val json = getString(key)
        if (!Utils.isEmpty(json)) {
            try {
                val gson = Gson()
                val array = JsonParser().parse(json).asJsonArray
                for (elem in array) {
                    list.add(gson.fromJson(elem, cls))
                }
            } catch (e: Exception) {
            }
        }
        return list
    }
}