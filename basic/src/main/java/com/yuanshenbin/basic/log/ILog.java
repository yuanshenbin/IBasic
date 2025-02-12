package com.yuanshenbin.basic.log;


import com.yuanshenbin.basic.config.BasicOptions;

public class ILog {
    private static LogAbstract instance;

    public static LogAbstract initialize(LogAbstract logAbstract) {
        if (instance == null) {
            instance = logAbstract;
        }
        return instance;
    }

    
    public static void i(String tag, String msg) {
        if (instance!=null&& BasicOptions.getInstance().isDebug()){
            instance.i(tag, msg);
        }
    }


    public static void i(String msg) {
        if (instance!=null&& BasicOptions.getInstance().isDebug()){
            instance.i(msg);
        }
    }


    public static void e(String tag, String msg) {
        if (instance!=null&& BasicOptions.getInstance().isDebug()){
            instance.e(tag, msg);
        }
    }


    public static void e(String msg) {
        if (instance!=null&& BasicOptions.getInstance().isDebug()){
            instance.e(msg);
        }
    }


    public static void v(String tag, String msg) {
        if (instance!=null&& BasicOptions.getInstance().isDebug()){
            instance.v(tag, msg);
        }
    }


    public static void d(String msg) {
        if (instance!=null&& BasicOptions.getInstance().isDebug()){
            instance.d(msg);
        }
    }


    public static void d(String tag, String msg) {
        if (instance!=null){
            instance.d(tag, msg);
        }
    }


    public static void w(String msg) {
        if (instance!=null){
            instance.w(msg);
        }
    }


    public static void w(String tag, String msg) {
        if (instance!=null){
            instance.w(tag, msg);
        }
    }
}
