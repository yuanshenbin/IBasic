package com.yuanshenbin.basic.startup.run;

import android.content.Context;
import android.os.Process;

import com.yuanshenbin.basic.startup.Result;
import com.yuanshenbin.basic.startup.Startup;
import com.yuanshenbin.basic.startup.manager.StartupCacheDelayManager;
import com.yuanshenbin.basic.startup.manager.StartupCacheManager;
import com.yuanshenbin.basic.startup.manager.StartupCostTimesManager;
import com.yuanshenbin.basic.startup.manager.StartupManager;


/**
 * author : yuanshenbin
 * time   : 2023/1/5
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public class StartupRunnable implements Runnable {
    private StartupManager startupManager;
    private Startup<?> startup;
    private Context context;

    public StartupRunnable(Context context, Startup<?> startup, StartupManager startupManager) {
        this.context = context;
        this.startup = startup;
        this.startupManager = startupManager;
    }

    @Override
    public void run() {
        Process.setThreadPriority(startup.getThreadPriority());
        startup.toWait();
        StartupCostTimesManager.getInstance().recordStart(startup);
        Object result =startup.create(context);
        StartupCostTimesManager.getInstance().recordEnd(startup);

        if(result!=null&&result instanceof  Boolean&&!(Boolean) result){
            StartupCacheDelayManager.getInstance().saveInitializedComponent(startup,new Result(result));
        }
        //保存任务的返回值
        StartupCacheManager.getInstance().saveInitializedComponent(startup.getClass(),new Result(result));
        //当前任务执行完成后，调用后续任务的toNotify（）
        startupManager.notifyChildren(startup);
    }
}
