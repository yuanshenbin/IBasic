package com.yuanshenbin.basic.util

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateUtils  {

    companion object {



        @JvmStatic
        fun getMillisToString(millis: Long, format: Type): String {
            return getMillisToString(millis, SimpleDateFormat(format.value))
        }
        @JvmStatic
        fun getMillisToString(millis: Long, format: DateFormat): String {
            return format.format(Date(millis))
        }
        @JvmStatic
        fun getMillisToString(millis: String?, format: Type): String {
            return getMillisToString(millis, SimpleDateFormat(format.value))
        }
        @JvmStatic
        fun getMillisToString(millis: String?, format: DateFormat): String {
            return try {
                format.format(format.parse(millis))
            } catch (e: ParseException) {
                e.printStackTrace()
                ""
            }
        }
        @JvmStatic
        fun getDateToString(date: Date?, format: Type): String {
            return getDateToString(date, SimpleDateFormat(format.value))
        }
        @JvmStatic
        fun getDateToString(date: Date?, format: DateFormat): String {
            return format.format(date)
        }
        @JvmStatic
        fun getStringToLong(millis: String?, format: Type): Long {
            return getStringToLong(millis, SimpleDateFormat(format.value))
        }
        @JvmStatic
        fun getStringToLong(millis: String?, format: DateFormat): Long {
            return try {
                val date = format.parse(millis)
                date.time
            } catch (e: ParseException) {
                e.printStackTrace()
                0
            }
        }

        /**
         * * <pre>
         * HH:mm    15:44
         * h:mm a    3:44 下午
         * HH:mm z    15:44 CST
         * HH:mm Z    15:44 +0800
         * HH:mm zzzz    15:44 中国标准时间
         * HH:mm:ss    15:44:40
         * yyyy-MM-dd    2016-08-12
         * yyyy-MM-dd HH:mm    2016-08-12 15:44
         * yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
         * yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
         * EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
         * yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
         * yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
         * yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
         * K:mm a    3:44 下午
         * EEE, MMM d, ''yy    星期五, 八月 12, '16
         * hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
         * yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
         * EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
         * yyMMddHHmmssZ    160812154440+0800
         * yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
         * 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    DATE(2016-08-12) TIME(15:44:40) 中国标准时间
        </pre> *
         */


    }
    enum class Type(var value: String) {
        HH_mm("HH:mm"),  //15:44
        h_mm("h:mm"),  //3:44
        HH_mm_ss("HH:mm:ss"),  //15:44:40
        yyyy_MM_dd("yyyy-MM-dd"), yyyy_MM_dd_SPACE_HH_mm("yyyy-MM-dd HH:mm"), yyyy_MM_dd_SPACE_HH_mm_ss("yyyy-MM-dd HH:mm:ss");

    }
}