package com.yuanshenbin.basic.startup.manager;

import android.content.Context;
import android.os.Looper;

import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.startup.Result;
import com.yuanshenbin.basic.startup.Startup;
import com.yuanshenbin.basic.startup.StartupSortStore;
import com.yuanshenbin.basic.startup.run.StartupRunnable;
import com.yuanshenbin.basic.startup.sort.TopologySort;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   :
 */
public class StartupManager {
    private CountDownLatch awaitCountDownLatch;

    private Context context;
    private List<Startup<?>> startupList;
    private StartupSortStore startupSortStore;

    public StartupManager(Context context, List<Startup<?>> startupList, CountDownLatch awaitCountDownLatch ) {
        this.context = context;
        this.startupList = startupList;
        this.awaitCountDownLatch = awaitCountDownLatch;
    }

    public StartupManager start() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("请在主线程调用！");
        }
        startupSortStore = TopologySort.sort(startupList);
        for (Startup<?> startup : startupSortStore.getResult()) {
            //执行程序员任务

            StartupRunnable startupRunnable = new StartupRunnable(context, startup, this);
            if (startup.callCreateOnMainThread()) {
                startupRunnable.run();
            } else {
                startup.executor().execute(startupRunnable);
            }
//            //保存执行返回执
//            StartupCacheManager.getInstance().saveInitializedComponent(startup.getClass(), new Result(result));

        }
        return this;
    }

    public void notifyChildren(Startup<?> startup) {
        if (!startup.callCreateOnMainThread()
                &&
                startup.waitOnMainThread()) {
            awaitCountDownLatch.countDown();
        }

        //获得已经完成的当前任务的所有子任务
        if (startupSortStore.getStartupChildrenMap().containsKey(startup.getClass())) {
            List<Class<? extends Startup>> childStartupCls = startupSortStore
                    .getStartupChildrenMap().get(startup.getClass());

            for (Class<? extends Startup> cls : childStartupCls) {
                //通知子任务 startup 父任务已经完成
                Startup<?> childStartup = startupSortStore.getStartupMap().get(cls);
                childStartup.toNotify();
            }
        }


        if (StartupCacheManager.getInstance().size() == startupList.size()) {
            if (BasicOptions.getInstance().isDebug()) {
                StartupCostTimesManager.getInstance().printAll();
            }
            StartupCacheManager.getInstance().clear();
            if (StartupCacheManager.getInstance().size() != 0 && StartupCacheDelayManager.getInstance().size() == StartupCacheManager.getInstance().size()) {
                StartupCacheDelayManager.getInstance().clear();
                StartupCacheConvertManager.getInstance().clear();
            }

        }

    }

    public void await() {
        try {
            awaitCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //基于分阶段处理任务，不能用单利
    public static class Builder {
        private List<Startup<?>> startupList = new ArrayList<>();
        private List<Class<? extends Startup>> startupConvertList = new ArrayList<>();

        public Builder addStartup(Startup<?> startup) {
            this.startupList.add(startup);
            return this;
        }

        public Builder addAllStartup(List<Startup<?>> startupList) {
            this.startupList.addAll(startupList);
            return this;
        }

        public Builder addStartupConvert(Class<? extends Startup> startup) {
            this.startupConvertList.add(startup);
            return this;
        }

        public Builder addAllStartupConvert(List<Class<? extends Startup> > startupList) {
            this.startupConvertList.addAll(startupList);
            return this;
        }

        public StartupManager build(Context context) {
            StartupCostTimesManager.getInstance().clear();

            //把需要特殊处理的交给StartupConvertManager
            for (Class<? extends Startup> aClass : startupConvertList) {
                StartupCacheConvertManager.getInstance().saveInitializedComponent(aClass, new Result(null));
            }

            //记录有多少个在子线程执行，又需要主线程等待的任务
            AtomicInteger needAwaitCount = new AtomicInteger();
            for (Startup<?> startup : startupList) {
                if (!startup.callCreateOnMainThread() && startup.waitOnMainThread()) {
                    needAwaitCount.incrementAndGet();//i++
                }
            }


            //根据任务创建新建1个必锁
            CountDownLatch awaitCountDownLatch = new CountDownLatch(needAwaitCount.get());
            return new StartupManager(context, startupList, awaitCountDownLatch);
        }
    }
}
