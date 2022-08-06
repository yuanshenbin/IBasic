package com.yuanshenbin.basic.util

import java.text.DecimalFormat


/**
 * author : yuanshenbin
 * time   : 2018/11/8
 * desc   :
 */
class DoubleUtils {

    companion object{
        /**
         * @param number
         * @return 保留两位小数
         * @author rec
         */
        @JvmStatic
        fun getDoubleNumber(number: Double): String {
            val df = DecimalFormat("0.00")
            return df.format(number)
        }

        /**
         * @param number
         * @return 保留两位小数
         * @author rec
         */
        @JvmStatic
        fun getDoubleNumber1(number: Double): String {
            val df = DecimalFormat("#,##0.00")
            return df.format(number)
        }
        @JvmStatic
        fun getMoney(price: Double): String {
            return if (price <= 0) {
                "-"
            } else "¥" + getDoubleNumber(price)
        }
        @JvmStatic
        fun getMoney1(price: Double): String {
            return if (price <= 0) {
                "-"
            } else "¥" + getDoubleNumber1(price)
        }
    }

}