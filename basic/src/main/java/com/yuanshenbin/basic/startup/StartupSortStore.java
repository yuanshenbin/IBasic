package com.yuanshenbin.basic.startup;

import java.util.List;
import java.util.Map;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public  class StartupSortStore {
    //所有任务排序后的结果
    List<Startup<?>> result;
    //任务原图
    Map<Class<? extends Startup>, Startup<?>> startupMap;
    //当前任务的依赖关系
    Map<Class<? extends Startup>,List<Class<? extends Startup>>> startupChildrenMap;

    public StartupSortStore(List<Startup<?>> result, Map<Class<? extends Startup>, Startup<?>> startupMap, Map<Class<? extends Startup>,List<Class<? extends Startup>>> startupChildrenMap){

        this.result=result;
        this.startupMap=startupMap;
        this.startupChildrenMap=startupChildrenMap;
    }

    public List<Startup<?>> getResult() {
        return result;
    }

    public void setResult(List<Startup<?>> result) {
        this.result = result;
    }

    public Map<Class<? extends Startup>, Startup<?>> getStartupMap() {
        return startupMap;
    }

    public void setStartupMap(Map<Class<? extends Startup>, Startup<?>> startupMap) {
        this.startupMap = startupMap;
    }

    public Map<Class<? extends Startup>, List<Class<? extends Startup>>> getStartupChildrenMap() {
        return startupChildrenMap;
    }

    public void setStartupChildrenMap(Map<Class<? extends Startup>, List<Class<? extends Startup>>> startupChildrenMap) {
        this.startupChildrenMap = startupChildrenMap;
    }
}
