package com.yuanshenbin.basic.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * author : yuanshenbin
 * time   : 2018/11/9
 * desc   :
 */
class DecimalUtils  {


    companion object {


        /**
         * double类型，保留两位小数，
         * 当为0时，显示0.00
         *
         * @param number
         * @return
         */
        @JvmStatic
        fun getDecimalNumber(number: Double): String {
            val df = DecimalFormat("0.00")
            df.roundingMode = RoundingMode.HALF_UP
            return if (number == 0.0) {
                "0.00"
            } else {
                df.format(number)
            }
        }

        /**
         * @param number
         * @return 保留两位小数
         * @author rec
         */
        @JvmStatic
        fun getDoubleNumber(number: BigDecimal?): String {
            val df = DecimalFormat("0.00")
            df.roundingMode = RoundingMode.HALF_UP
            return if (number == null) {
                "0.00"
            } else {
                df.format(number)
            }
        }

        /**
         * double类型，保留两位小数，
         * 当为0时，显示0.00
         *
         * @param number
         * @return
         */
        @JvmStatic
        fun getDecimalNumber(number: Double, roundingMode: RoundingMode?): String {
            val df = DecimalFormat("0.00")
            df.roundingMode = roundingMode
            return if (number == 0.0) {
                "0.00"
            } else {
                df.format(number)
            }
        }

    }
}