package com.yuanshenbin.basic.startup;

import android.content.Context;

import androidx.annotation.Nullable;

import java.util.List;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public interface Startup<T> extends Dispatcher {
    /**
     * 给程序写任务逻辑使用
     *
     * @param context
     * @return
     */
    @Nullable
    T create(Context context);


    /**
     * 本任务以来哪些任务
     *
     * @return
     */
    List<Class<? extends Startup<?>>> dependencies();

    /**
     * 入度数
     *
     * @return
     */
    int getDependenciesCount();


    void removeDependencies(Class<? extends Startup<?>> startup);


}
