package com.yuanshenbin.basic.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * author : yuanshenbin
 * time   : 2018/11/9
 * desc   :
 */

public class DecimalUtils {


    /**
     * double类型，保留两位小数，
     * 当为0时，显示0.00
     *
     * @param number
     * @return
     */
    public static String getDecimalNumber(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (number == 0) {
            return "0.00";
        } else {
            return df.format(number);
        }
    }
    /**
     * @param number
     * @return 保留两位小数
     * @author rec
     */
    public static String getDoubleNumber(BigDecimal number) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        if (number == null) {
            return "0.00";
        } else {
            return df.format(number);
        }
    }

    /**
     * double类型，保留两位小数，
     * 当为0时，显示0.00
     *
     * @param number
     * @return
     */
    public static String getDecimalNumber(double number, RoundingMode roundingMode) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(roundingMode);
        if (number == 0) {
            return "0.00";
        } else {
            return df.format(number);
        }
    }
}
