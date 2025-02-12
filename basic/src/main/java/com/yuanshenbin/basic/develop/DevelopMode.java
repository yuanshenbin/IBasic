package com.yuanshenbin.basic.develop;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.db.DBManager;
import com.yuanshenbin.basic.develop.ui.DevelopModeActivity;
import com.yuanshenbin.basic.model.ErrorModel;
import com.yuanshenbin.basic.model.LogModel;
import com.yuanshenbin.basic.model.LogRecorder;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2018/8/29
 * desc   :
 */

public class DevelopMode {
    private int clickCount = 0;
    private static DevelopMode instance;

    private final static int DEVELOP_MODE_STEP_COUNT = 10;
    private long lastTime = 0;

    private List<Activity> mStacks = new ArrayList<>();


    private DevelopConfig mDevelopConfig;

    public DevelopConfig getDevelopConfig() {
        return mDevelopConfig;
    }

    public List<SystemApiModel> getApiModels() {
        for (int i = 0; i < mApiModels.size(); i++) {
            mApiModels.get(i).setChecked(false);
        }
        return mApiModels;
    }

    private List<SystemApiModel> mApiModels = new ArrayList<>();

    public void add(Activity activity) {
        mStacks.add(activity);
    }

    public void remove(Activity activity) {
        mStacks.remove(activity);
    }

    public void clear() {
        for (Activity stack : mStacks) {
            stack.finish();
        }
    }


    public static synchronized DevelopMode getInstance() {
        if (instance == null)
            synchronized (DevelopMode.class) {
                instance = new DevelopMode();
            }
        return instance;
    }

    public void InitializationConfig(DevelopConfig config) {
        this.mDevelopConfig = config;

        mApiModels.clear();
        mApiModels.addAll(config.getApiModels());
    }


    /**
     * 进入开发者模式
     * 需要传入一个view。
     * 通过这个view的长按事件来触发开发者模式
     * 模仿android系统进入开发者模式的方法进行
     */
    public void setUpDevelopMode(View view, final OnSystemApiSwitchListener listener) {
        if (mDevelopConfig == null) {
            throw new ExceptionInInitializerError("Please invoke deevelopConfig~");
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCount++;
                if (clickCount == 1) {
                    lastTime = System.currentTimeMillis();
                }
                if (clickCount >= DEVELOP_MODE_STEP_COUNT) {
                    clickCount = 0;
                    if (System.currentTimeMillis() - lastTime <= 5000) {
                        setUpDevelopMode(view.getContext(), listener);
                    }
                    lastTime = 0;
                }
            }
        });

    }

    private static FloatViewPresenter presenter;
    private OnSystemApiSwitchListener mListener;

    public OnSystemApiSwitchListener getListener() {
        return mListener;
    }

    /**
     * 同时开放一个无参数的api给业务使用，
     * 可能前端会使用不同的方式来进行调用而进入开发者模式
     */
    public void setUpDevelopMode(Context context, OnSystemApiSwitchListener listener) {
        mListener = listener;
        if (mDevelopConfig.getMode() == DevelopConfig.Mode.WINDOW) {
            if (presenter == null) {
                presenter = new FloatViewPresenter();
            }
            presenter.initFloatView(context);
        } else if (mDevelopConfig.getMode() == DevelopConfig.Mode.VIEW) {
            WindowManagerInstance.getInstance().hasView = true;
            context.startActivity(new Intent(context, DevelopModeActivity.class));

        } else if (mDevelopConfig.getMode() == DevelopConfig.Mode.NOTICE) {
            WindowManagerInstance.getInstance().hasView = true;
            showNotify(context);
        }
    }


    private static NotificationManager mNotificationManager;

    private static Notification mNotification;

    /**
     * 显示常驻通知栏
     */
    private void showNotify(Context context) {

        if (mNotificationManager == null || mNotification == null) {
            NotificationCompat.Builder mBuilder = null;
            String PUSH_CHANNEL_ID = String.valueOf(context.getPackageName().hashCode());
            String PUSH_CHANNEL_NAME = getAppName(context);
            mBuilder = new NotificationCompat.Builder(context, PUSH_CHANNEL_ID);

            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
                if (mNotificationManager != null) {
                    mNotificationManager.createNotificationChannel(channel);
                }
            }
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, DevelopModeActivity.class), 0);
            mBuilder.setSmallIcon(R.drawable.basic_develop_mode_ic_launcher_round)
                    .setTicker("开发者权限已打开- -！")
                    .setContentTitle(PUSH_CHANNEL_NAME + " → 开发者调试常驻")
                    .setContentIntent(pendingIntent);
            mNotification = mBuilder.build();
            //设置通知  消息  图标
            mNotification.icon = DevelopMode.getInstance().getDevelopConfig().getWindowIcon();
            //在通知栏上点击此通知后自动清除此通知
            mNotification.flags |= Notification.FLAG_ONGOING_EVENT;//FLAG_ONGOING_EVENT 在顶部常驻，可以调用下面的清除方法去除  FLAG_AUTO_CANCEL  点击和清理可以去调
            //设置显示通知时的默认的发声、震动、Light效果
            mNotification.defaults = Notification.DEFAULT_VIBRATE;
            //设置发出消息的内容
            mNotification.tickerText = "开发者";
            //设置发出通知的时间
            mNotification.when = System.currentTimeMillis();

            mNotificationManager.notify(0, mNotification);
        } else {

            mNotificationManager.notify(0, mNotification);
        }

    }

    public void addLog(LogModel logModel) {
        if (WindowManagerInstance.getInstance().hasView) {
            LogRecorder.getInstance().addLog(logModel);
        }
    }


    public List<LogModel> getLogModels() {
        return LogRecorder.getInstance().getLogModels();
    }

    public void setLogModels(List<LogModel> logModels) {
        LogRecorder.getInstance().setLogModels(logModels);
    }

    private static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "开发者模式";

    }

    public void shareFile(Context context) {

        DBManager mDBManager = new DBManager(context);
        try {
            mDBManager.open();
        } catch (SQLDataException throwables) {
            throwables.printStackTrace();
        }

        try {
            ErrorModel model =mDBManager.loadLast();

            Intent it = new Intent("android.intent.action.SEND");
            it.putExtra("android.intent.extra.SUBJECT", "发送catch日志");
            it.putExtra("android.intent.extra.TEXT", model.getDesc());
            it.setType("text/plain");
            context.startActivity(Intent.createChooser(it, "发送catch日志"));
        }catch (Exception e){
            Toast.makeText(context,"暂无日志",Toast.LENGTH_SHORT).show();

        }finally {
            mDBManager.close();
        }
    }
}
