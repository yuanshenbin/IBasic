package com.yuanshenbin.basic.util

import android.content.Context

/**
 * author : 作者不明，旺盛拷贝的
 * time   : 2017/12/29
 * desc   :dp、sp、px转换
 */
class DisplayUtils  {

    companion object {


        /**
         * 将px值转换为dip或dp值，保证尺寸大小不变
         *
         * @param context
         * @param pxValue （DisplayMetrics类中属性density）
         * @return
         */
        @JvmStatic
        fun px2dip(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        /**
         * 将dip或dp值转换为px值，保证尺寸大小不变
         *
         * @param context
         * @param dipValue （DisplayMetrics类中属性density）
         * @return
         */
        @JvmStatic
        fun dip2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }

        /**
         * 将px值转换为sp值，保证文字大小不变
         *
         * @param context
         * @param pxValue （DisplayMetrics类中属性scaledDensity）
         * @return
         */
        @JvmStatic
        fun px2sp(context: Context, pxValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }

        /**
         * 将sp值转换为px值，保证文字大小不变
         *
         * @param context
         * @param spValue （DisplayMetrics类中属性scaledDensity）
         * @return
         */
        @JvmStatic
        fun sp2px(context: Context, spValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (spValue * fontScale + 0.5f).toInt()
        }

        /**
         * 根据手机的宽
         */
        @JvmStatic
        fun getWidth(context: Context): Int {
            return context.resources.displayMetrics.widthPixels
        }

        /**
         * 根据手机的高
         */
        @JvmStatic
        fun getHeight(context: Context): Int {
            return context.resources.displayMetrics.heightPixels
        }

        @JvmStatic
        fun getStatusBarHeight(context: Context): Int {
            val resources = context.resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }
    }
}