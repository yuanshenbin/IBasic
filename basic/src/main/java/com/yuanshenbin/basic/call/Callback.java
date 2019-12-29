package com.yuanshenbin.basic.call;

/**
 * author : yuanshenbin
 * time   : 2018/4/5
 * desc   :
 */

public abstract class Callback<T> {

    public abstract void ok(T t);

    public void cancel(T t) {

    }

    public void close(){

    }
}
