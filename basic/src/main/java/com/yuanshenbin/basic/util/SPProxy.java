package com.yuanshenbin.basic.util;

import com.tencent.mmkv.MMKV;

/**
 * author : yuanshenbin
 * time   : 2022-10-21
 * desc   :
 */
public interface SPProxy {
    MMKV getSP(String key);
}
