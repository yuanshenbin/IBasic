package com.yuanshenbin.basic.util

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.yuanshenbin.basic.util.JsonUtils.Companion.`object`
import com.yuanshenbin.basic.util.Utils.Companion.isEmpty
import java.util.*

/**
 * author : yuanshenbin
 * time   : 2021-09-21
 * desc   :
 */
object SPUtils {
    private var instance: SPProxy? = null

    @JvmStatic
    fun initialize(proxy: SPProxy?): SPProxy? {
        if (instance == null) {
            instance = proxy
        }
        return instance
    }

    @JvmStatic
    fun removeValuesForKeys(key: String?) {
        instance!!.filter(key)!!.removeValueForKey(key)
    }

    @JvmStatic
    fun putParcelable(key: String?, value: Parcelable?) {
        instance!!.filter(key)!!.encode(key, value)
    }

    @JvmStatic
    fun <T : Parcelable?> getParcelable(key: String?, value: Class<T>?): T? {
        return instance!!.filter(key)!!.decodeParcelable(key, value)
    }

    @JvmStatic
    fun putInt(key: String?, value: Int) {
        instance!!.filter(key)!!.encode(key, value)
    }

    @JvmStatic
    fun getInt(key: String?): Int {
        return instance!!.filter(key)!!.decodeInt(key)
    }

    @JvmStatic
    fun putString(key: String?, value: String?) {
        instance!!.filter(key)!!.encode(key, value)
    }

    @JvmStatic
    fun getString(key: String?): String? {
        return instance!!.filter(key)!!.decodeString(key)
    }

    @JvmStatic
    fun putBoolean(key: String?, value: Boolean) {
        instance!!.filter(key)!!.encode(key, value)
    }

    @JvmStatic
    fun getBoolean(key: String?): Boolean {
        return instance!!.filter(key)!!.decodeBool(key)
    }

    @JvmStatic
    fun putLong(key: String?, value: Long) {
        instance!!.filter(key)!!.encode(key, value)
    }

    @JvmStatic
    fun getLong(key: String?): Long {
        return instance!!.filter(key)!!.decodeLong(key)
    }

    @JvmStatic
    fun putFloat(key: String?, value: Float) {
        instance!!.filter(key)!!.encode(key, value)
    }

    @JvmStatic
    fun getFloat(key: String?): Float {
        return instance!!.filter(key)!!.decodeFloat(key)
    }

    @JvmStatic
    fun <T> getObject(key: String?, cls: Class<T>?): T? {
        return try {
            `object`(getString(key), cls)
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
        if (!isEmpty(json)) {
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