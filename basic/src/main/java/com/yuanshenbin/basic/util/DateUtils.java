package com.yuanshenbin.basic.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

    /**
     * * <pre>
     *                                        HH:mm    15:44
     *                                       h:mm a    3:44 下午
     *                                      HH:mm z    15:44 CST
     *                                      HH:mm Z    15:44 +0800
     *                                   HH:mm zzzz    15:44 中国标准时间
     *                                     HH:mm:ss    15:44:40
     *                                   yyyy-MM-dd    2016-08-12
     *                             yyyy-MM-dd HH:mm    2016-08-12 15:44
     *                          yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     *                     yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     *                EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     *                     yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     *                   yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     *                 yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     *                                       K:mm a    3:44 下午
     *                             EEE, MMM d, ''yy    星期五, 八月 12, '16
     *                        hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     *                 yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     *                   EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     *                                yyMMddHHmmssZ    160812154440+0800
     *                   yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     * </pre>
     */


    public static enum Type {
        HH_mm("HH:mm"),//15:44
        h_mm("h:mm"),//3:44
        HH_mm_ss("HH:mm:ss"),//15:44:40
        yyyy_MM_dd("yyyy-MM-dd"),
        yyyy_MM_dd_SPACE_HH_mm("yyyy-MM-dd HH:mm"),
        yyyy_MM_dd_SPACE_HH_mm_ss("yyyy-MM-dd HH:mm:ss");

        public String value;

        Type(String s) {
            this.value = s;
        }
    }


    public static String getMillisToString(long millis, Type format) {
        return getMillisToString(millis, new SimpleDateFormat(format.value));
    }


    public static String getMillisToString(long millis, final DateFormat format) {
        return format.format(new Date(millis));
    }


    public static String getMillisToString(String millis, Type format) {
        return getMillisToString(millis, new SimpleDateFormat(format.value));
    }


    public static String getMillisToString(String millis, final DateFormat format) {
        try {
            return format.format(format.parse(millis));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDateToString(Date date, Type format) {
        return getDateToString(date, new SimpleDateFormat(format.value));
    }

    public static String getDateToString(Date date, final DateFormat format) {
        return format.format(date);
    }

    public static long getStringToLong(String millis, Type format) {
        return getStringToLong(millis, new SimpleDateFormat(format.value));
    }

    public static long getStringToLong(String millis, final DateFormat format) {
        try {
            Date date = format.parse(millis);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

