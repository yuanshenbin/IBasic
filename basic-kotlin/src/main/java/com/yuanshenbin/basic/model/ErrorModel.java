package com.yuanshenbin.basic.model;

/**
 * author : yuanshenbin
 * time   : 2023/7/20
 * desc   :
 */
public class ErrorModel {

    private  String desc;
    private  String createTime;
    private  String error;
    private  String customCrashData;
    private  long id;
    private  int upload;

    public int getUpload() {
        return upload;
    }

    public void setUpload(int upload) {
        this.upload = upload;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCustomCrashData() {
        return customCrashData;
    }

    public void setCustomCrashData(String customCrashData) {
        this.customCrashData = customCrashData;
    }
}
