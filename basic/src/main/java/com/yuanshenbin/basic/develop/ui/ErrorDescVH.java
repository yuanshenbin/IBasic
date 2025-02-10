package com.yuanshenbin.basic.develop.ui;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.base.BasicViewHolder;
import com.yuanshenbin.basic.model.ErrorModel;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * author : yuanshenbin
 * time   : 2023/7/20
 * desc   :
 */
public  class ErrorDescVH  extends BasicViewHolder {

    public RecyclerView swipe_target;
    public TextView tv_title;
    public ImageView layout_back;
    public TextView tv_right_operate;
    @Override
    protected int initLayoutId() {
        return 0;
    }

    public ErrorDescVH(@NotNull ViewGroup rootView) {
        super(rootView);
        swipe_target =rootView.findViewById(R.id.swipe_target);
        tv_title =rootView.findViewById(R.id.tv_title);
        layout_back =rootView.findViewById(R.id.layout_back);
        tv_right_operate =rootView.findViewById(R.id.tv_right_operate);
    }
    public static String getBuildDateAsString(@NonNull Context context, @NonNull DateFormat dateFormat) {
        long buildDate;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ZipFile zf = new ZipFile(ai.sourceDir);

            //If this failed, try with the old zip method
            ZipEntry ze = zf.getEntry("classes.dex");
            buildDate = ze.getTime();


            zf.close();
        } catch (Exception e) {
            buildDate = 0;
        }

        if (buildDate > 631152000000L) {
            return dateFormat.format(new Date(buildDate));
        } else {
            return null;
        }
    }
    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            return "Unknown";
        }
    }
    public static String getDeviceModelName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }
    private static String capitalize(@Nullable String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
    public ErrorModel getAllErrorDetailsFromIntent(@NonNull Context context, @NonNull Intent intent) {
        //I don't think that this needs localization because it's a development string...
        ErrorModel model =new ErrorModel();
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

        //Get build date
        String buildDateAsString = getBuildDateAsString(context, dateFormat);

        //Get app version
        String versionName = getVersionName(context);

        String errorDetails = "";

        errorDetails += "Build version: " + versionName + " \n";
        if (buildDateAsString != null) {
            errorDetails += "Build date: " + buildDateAsString + " \n";
        }
        errorDetails += "Current date: " + dateFormat.format(currentDate) + " \n";
        //Added a space between line feeds to fix #18.
        //Ideally, we should not use this method at all... It is only formatted this way because of coupling with the default error activity.
        //We should move it to a method that returns a bean, and let anyone format it as they wish.
        errorDetails += "Device: " + getDeviceModelName() + " \n";
        errorDetails += "OS version: Android " + Build.VERSION.RELEASE + " (SDK " + Build.VERSION.SDK_INT + ") \n \n";
        errorDetails += "Stack trace:  \n";
        String stacktrace=CustomActivityOnCrash.getStackTraceFromIntent(intent);


        String[] temp =stacktrace.split("\n");

        errorDetails += stacktrace;

        String activityLog = CustomActivityOnCrash.getActivityLogFromIntent(intent);

        if (activityLog != null) {
            errorDetails += "\nUser actions: \n";
            errorDetails += activityLog;
        }
        try {
            model.setError(temp[0]);
        }catch (Exception  e){
            model.setError(activityLog);
        }

        String customCrashData = CustomActivityOnCrash.getCustomCrashDataFromIntent(intent);
        if (customCrashData != null) {
            errorDetails += "\nAdditional data: \n";
            errorDetails += customCrashData;
        }


        model.setCustomCrashData(customCrashData);
        model.setDesc(errorDetails);
        model.setCreateTime(dateFormat.format(currentDate) );
        return model;
    }

}
