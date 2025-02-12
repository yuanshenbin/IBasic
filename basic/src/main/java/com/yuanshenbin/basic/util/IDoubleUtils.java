package com.yuanshenbin.basic.util;

import java.text.DecimalFormat;

/**
 * author : yuanshenbin
 * time   : 2018/11/8
 * desc   :
 */

public class IDoubleUtils {
    /**
     * @param number
     * @return 保留两位小数
     * @author rec
     */
    public static String getDoubleNumber(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }

    /**
     * @param number
     * @return 保留两位小数
     * @author rec
     */
    public static String getDoubleNumber1(double number) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return df.format(number);
    }

    public static String getMoney(double price) {
        if (price <= 0) {
            return "-";
        }
        return "¥" + getDoubleNumber(price);
    }

    public static String getMoney1(double price) {
        if (price <= 0) {
            return "-";
        }
        return "¥" + getDoubleNumber1(price);
    }
}
