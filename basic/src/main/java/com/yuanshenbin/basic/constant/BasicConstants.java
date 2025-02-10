package com.yuanshenbin.basic.constant;

import com.yuanshenbin.basic.config.BasicOptions;

/**
 * author : yuanshenbin
 * time   : 2018/10/24
 * desc   :
 */

public class BasicConstants {
    public static final int LOADING_STATE1 = 1;//没有数据
    public static final int LOADING_STATE2 = 2;//分页 不需要全局loading//或者有数据下啦也不需要loading
    public static final int LOADING_STATE3 = 3;//有数据加载无背景的loading
    public static final int LOADING_STATE4 = 4;//有数据下啦，没有loading
    public static  int PAGESIZE = BasicOptions.getInstance().getPageSize();

    public static  int LUBAN_COUNT = BasicOptions.getInstance().getCompressCount();
    public static  int COMPRESS_SIZE = BasicOptions.getInstance().getCompressSize();//图片压缩多少k


    public static final String DEFAULT = "default";
    public static final String DEFAULT_ERROR = "default_error";//rxjava会用到区分请求和自己抛出异常

}
