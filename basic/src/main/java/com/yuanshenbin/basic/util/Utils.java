package com.yuanshenbin.basic.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import java.util.List;
import java.util.Map;

/**
 * author : yuanshenbin
 * time   : 2018/8/27
 * desc   : 常用工具类
 */

public class Utils {

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param context  上下文
     * @param phoneNum 电话
     */
    public static void callPhone(Context context, String phoneNum) {
        callPhone(context, phoneNum, Intent.ACTION_DIAL);
    }

    /**
     * 拨打电话
     *
     * @param context  上下文
     * @param phoneNum 电话
     * @param action   Intent.ACTION_CALL,Intent.ACTION_DIAL
     */
    public static void callPhone(Context context, String phoneNum, String action) {
        if (isEmpty(phoneNum) || context == null || isEmpty(action)) {
            return;
        }
        Intent intent = new Intent(action);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 判断为空，比系统的多层trim
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.toString().trim().length() == 0;
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.toString().trim().length() == 0;
    }

    /**
     * 判断集合为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 判断集合为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    /**
     * 判断为空，比系统的多层trim
     *
     * @param str
     * @return
     */
    public static String noNull(String str) {
        return str == null || str.toString().trim().length() == 0 ? "" : str;
    }

    public static int strToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static double strToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0.00;
        }
    }

    public static long strToLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 打开系统设置
     *
     * @param context
     */
    public static void openSystemSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 打开系统GPS
     *
     * @param context
     */
    public static void openGPSSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    /**
     * 打开app设置
     *
     * @param context
     */
    public static void openAppSettings(Context context) {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    public static void copy(Context context, CharSequence text) {
        copy(context, text, "复制成功");
    }

    public static void copy(Context context, CharSequence text, String hint) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", text);
        cm.setPrimaryClip(mClipData);
        ToastUtils.shortToast(context, hint);
    }

    /**
     * 发送短信
     *
     * @param context
     * @param message
     * @param phone
     */
    public static void sendSMS(Context context, String message, String... phone) {
        String mobile = "";
        if (phone == null || phone.length == 0) {
            return;
        }
        if (mobile.length() == 1) {
            mobile = phone[0];
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < phone.length; i++) {
                sb.append(phone[i]);
                if (phone.length - 1 != i) {
                    sb.append(";");
                }
            }
        }
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + mobile));
        intent.putExtra("sms_body", message);
        context.startActivity(intent);
    }

    /**
     * 字符串截取变红
     *
     * @param content       整个字符串
     * @param changeContent 你要变色的字符
     */
    public static SpannableStringBuilder getSpannableString(String content, String changeContent, int color) {
        int fstart = content.indexOf(changeContent);
        int fend = fstart + changeContent.length();
        SpannableStringBuilder style = new SpannableStringBuilder(content);
        if (fend != 0 && fstart != -1) {
            style.setSpan(new ForegroundColorSpan(color), fstart, fend,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            return style;
        } else {
            return style;
        }
    }

    /**
     * 检查包是否存在
     *
     * @param packageName 包名
     * @return
     */
    public static boolean checkPackageName(Context context, String packageName) {
        if (Utils.isEmpty(packageName)) {
            return false;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    public static boolean isExistActivity(Context context, String className) {
        Intent intent = new Intent();
        intent.setClassName(context, className);
        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, 0);
        return !Utils.isEmpty(list);
    }
}
