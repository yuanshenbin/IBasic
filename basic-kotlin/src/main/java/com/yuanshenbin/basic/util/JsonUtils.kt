package com.yuanshenbin.basic.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yuanshenbin.basic.call.AdaptClass
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * author : yuanshenbin
 * time   : 2018/1/24
 * desc   :
 */
class JsonUtils  {

    companion object {
        private val gson = Gson()

        /**
         * json管理
         */
        @JvmStatic
        fun string(`object`: Any?): String {
            return gson.toJson(`object`)
        }
        @JvmStatic
        fun <T> `object`(json: String?, classOfT: Class<T>?): T {
            return gson.fromJson(json, classOfT)
        }
        @JvmStatic
        fun <T> `object`(json: String?, type: Type?): T {
            return gson.fromJson(json, type)
        }

        @JvmStatic
        fun <T> jsonToList(json: String?): List<T> {
            val type = object : TypeToken<List<T>?>() {}.type
            return Gson().fromJson(json, type)
        }
        @JvmStatic
        fun <T> getType(adaptClass: AdaptClass<T>): Type {
            return (adaptClass.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        }
    }

}