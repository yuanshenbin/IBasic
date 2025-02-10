package com.yuanshenbin.basic.startup;

import android.os.Process;

import com.yuanshenbin.basic.startup.manager.ExecutorManager;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public abstract class AndroidStartup<T> implements Startup<T> {
    //入度初始化
    private CountDownLatch waitCountDown = new CountDownLatch(getDependenciesCount());

    @Override
    public List<Class<? extends Startup<?>>> dependencies() {
        return null;
    }

    @Override
    public int getDependenciesCount() {
        List<Class<? extends Startup<?>>> dependencies = dependencies();
        return dependencies == null ? 0 : dependencies.size();
    }


    @Override
    public void toWait() {

        try {
            waitCountDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toNotify() {
        waitCountDown.countDown();
    }

    /**
     * 默认使用 ios线程
     *
     * @return
     */
    @Override
    public Executor executor() {
        return ExecutorManager.ioExecutor;
    }

    @Override
    public int getThreadPriority() {
        return Process.THREAD_PRIORITY_DEFAULT;
    }

    /**
     * 默认在主线程执行
     *
     * @return
     */
    @Override
    public boolean callCreateOnMainThread() {
        return false;
    }

    /**
     * 默认不需要主线程等待自己执行完成
     *
     * @return
     */
    @Override
    public boolean waitOnMainThread() {
        return false;
    }

    @Override
    public void removeDependencies(Class<? extends Startup<?>> startup) {
        if (dependencies() != null && dependencies().size() != 0) {
            dependencies().remove(startup);
        }
    }

}
