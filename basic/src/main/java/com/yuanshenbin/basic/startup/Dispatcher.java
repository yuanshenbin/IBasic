package com.yuanshenbin.basic.startup;

import java.util.concurrent.Executor;

/**
 * author : yuanshenbin
 * time   : 2023/1/5
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public  interface Dispatcher {
    /**
     * 返回是否在主线程
     * @return
     */
    boolean callCreateOnMainThread();


    /**
     * 让每个任务都制定在哪个线程
     * @return
     */
    Executor executor();

    /**
     * 制定线程的优先级
     * @return
     */
    int getThreadPriority();

    /**
     * 等待
     */
    void toWait();

    /**
     * 有父任务执行完成
     * 记数器-1
     */
    void  toNotify();

    /**
     * 是否要主线程等待该任务执行完成
     * callCreateOnMainThread() 方法返回false 才有意义
     * @return
     */
    boolean waitOnMainThread();
}
