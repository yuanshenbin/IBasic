package com.yuanshenbin.basic.log;

public abstract class LogAbstract {
    
    public static final String TAG="ysb";

    abstract void i(String tag, String msg);

    abstract void i(String msg);

    abstract void e(String tag, String msg);

    abstract void e(String msg);

    abstract void d(String tag, String msg);

    abstract void d(String msg);

    abstract void v(String tag, String msg);

    abstract void v(String msg);

    abstract void w(String tag, String msg);

    abstract void w(String msg);

}
