package com.yuanshenbin.basic.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan

/**
 * author : yuanshenbin
 * time   : 2018/8/27
 * desc   : 常用工具类
 */
class Utils  {

    companion object {


        /**
         * 拨打电话
         *
         * @param context  上下文
         * @param phoneNum 电话
         * @param action   Intent.ACTION_CALL,Intent.ACTION_DIAL
         */
        /**
         * 拨打电话（跳转到拨号界面，用户手动点击拨打）
         *
         * @param context  上下文
         * @param phoneNum 电话
         */
        @JvmOverloads
        @JvmStatic
        fun callPhone(context: Context?, phoneNum: String, action: String? = Intent.ACTION_DIAL) {
            if (isEmpty(phoneNum) || context == null || isEmpty(action)) {
                return
            }
            val intent = Intent(action)
            val data = Uri.parse("tel:$phoneNum")
            intent.data = data
            context.startActivity(intent)
        }


        /**
         * 判断为空，比系统的多层trim
         *
         * @param str
         * @return
         */
        @JvmStatic
        fun isEmpty(str: String?): Boolean {
            return str == null || str.toString().trim { it <= ' ' }.isEmpty()
        }
        @JvmStatic
        fun isEmpty(str: CharSequence?): Boolean {
            return str == null || str.toString().trim { it <= ' ' }.isEmpty()
        }

        /**
         * 判断集合为空
         *
         * @param list
         * @return
         */
        @JvmStatic
        fun isEmpty(list: List<*>?): Boolean {
            return list == null || list.isEmpty()
        }

        /**
         * 判断集合为空
         *
         * @param map
         * @return
         */
        @JvmStatic
        fun isEmpty(map: Map<*, *>?): Boolean {
            return map == null || map.isEmpty()
        }

        /**
         * 判断为空，比系统的多层trim
         *
         * @param str
         * @return
         */
        @JvmStatic
        fun noNull(str: String?): String {
            return if (str == null || str.toString().trim { it <= ' ' }.isEmpty()) "" else str
        }

        @JvmStatic
        fun strToInt(str: String): Int {
            return try {
                str.toInt()
            } catch (e: Exception) {
                0
            }
        }
        @JvmStatic
        fun strToDouble(str: String): Double {
            return try {
                str.toDouble()
            } catch (e: Exception) {
                0.00
            }
        }
        @JvmStatic
        fun strToLong(str: String): Long {
            return try {
                str.toLong()
            } catch (e: Exception) {
                0
            }
        }

        /**
         * 打开系统设置
         *
         * @param context
         */
        @JvmStatic
        fun openSystemSettings(context: Context) {
            val intent = Intent(Settings.ACTION_SETTINGS)
            context.startActivity(intent)
        }

        /**
         * 打开系统GPS
         *
         * @param context
         */
        @JvmStatic
        fun openGPSSettings(context: Context) {
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            context.startActivity(intent)
        }

        /**
         * 打开app设置
         *
         * @param context
         */
        @JvmStatic
        fun openAppSettings(context: Context) {
            val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:" + context.packageName)
            context.startActivity(intent)
        }
        @JvmStatic
        @JvmOverloads
        fun copy(context: Context, text: CharSequence?, hint: String? = "复制成功") {
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("Label", text)
            cm.setPrimaryClip(mClipData)
            ToastUtils.shortToast(context, hint)
        }

        /**
         * 发送短信
         *
         * @param context
         * @param message
         * @param phone
         */
        @JvmStatic
        fun sendSMS(context: Context, message: String?, vararg phone: String) {
            var mobile = ""
            if (phone == null || phone.isEmpty()) {
                return
            }
            if (mobile.length == 1) {
                mobile = phone[0]
            } else {
                val sb = StringBuffer()
                for (i in phone.indices) {
                    sb.append(phone[i])
                    if (phone.size - 1 != i) {
                        sb.append(";")
                    }
                }
            }
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:$mobile"))
            intent.putExtra("sms_body", message)
            context.startActivity(intent)
        }

        /**
         * 字符串截取变红
         *
         * @param content       整个字符串
         * @param changeContent 你要变色的字符
         */
        @JvmStatic
        fun getSpannableString(content: String, changeContent: String, color: Int): SpannableStringBuilder {
            val fstart = content.indexOf(changeContent)
            val fend = fstart + changeContent.length
            val style = SpannableStringBuilder(content)
            return if (fend != 0 && fstart != -1) {
                style.setSpan(ForegroundColorSpan(color), fstart, fend,
                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
                style
            } else {
                style
            }
        }

        /**
         * 检查包是否存在
         *
         * @param packageName 包名
         * @return
         */
        @JvmStatic
        fun checkPackageName(context: Context, packageName: String?): Boolean {
            if (isEmpty(packageName)) {
                return false
            }
            var packageInfo: PackageInfo? = null
            try {
                packageInfo = context.packageManager.getPackageInfo(packageName!!, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return packageInfo != null
        }

        /**
         * 检测activity是否存在
         *
         * @param context
         * @param className
         * @return
         */
        @JvmStatic
        fun isExistActivity(context: Context, className: String?): Boolean {
            val intent = Intent()
            intent.setClassName(context, className!!)
            val list = context.packageManager.queryIntentActivities(intent, 0)
            return !isEmpty(list)
        }

        /**
         * 打开系统浏览器
         *
         * @param context
         * @param hint
         * @param url
         */
        @JvmOverloads
        @JvmStatic
        fun openUrl(context: Context, url: String, hint: String? = "url:" + url + "无法正常打开") {
            try {
                val uri = Uri.parse(url)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            } catch (e: Exception) {
                ToastUtils.shortToast(context, hint)
            }
        }

        /**
         * 版本比较
         * @param currentVersion
         * @param newVersion
         * @return
         */
        @JvmStatic
        fun versionCompare(currentVersion: String, newVersion: String): Int {
            if (currentVersion == newVersion) {
                return 0
            }
            val verArr1 = currentVersion.split("\\.").toTypedArray()
            val verArr2 = newVersion.split("\\.").toTypedArray()
            var maxflag = 1
            var minLen = 0
            if (verArr1.size > verArr2.size) {
                minLen = verArr2.size
            } else {
                minLen = verArr1.size
                maxflag = 2
            }
            for (i in 0 until minLen) {
                if (Integer.valueOf(strToInt(verArr1[i])) - Integer.valueOf(strToInt(verArr2[i])) > 0) {
                    return 1
                } else if (Integer.valueOf(strToInt(verArr1[i])) - Integer.valueOf(strToInt(verArr2[i])) < 0) {
                    return -1
                }
            }
            if (maxflag == 1) {
                for (j in minLen until verArr1.size) {
                    if (Integer.valueOf(strToInt(verArr1[j])).toInt() > 0) {
                        return 1
                    }
                }
            } else {
                for (k in minLen until verArr2.size) {
                    if (Integer.valueOf(strToInt(verArr2[k])).toInt() > 0) {
                        return -1
                    }
                }
            }
            return 0
        }
    }
}