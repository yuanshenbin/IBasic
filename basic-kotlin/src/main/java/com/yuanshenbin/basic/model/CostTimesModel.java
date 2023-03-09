package com.yuanshenbin.basic.model;

/**
 * author : yuanshenbin
 * time   : 2023/1/9
 * desc   :
 */
public class CostTimesModel {
    private String name;
    private boolean callOnMainThread;
    private boolean waitOnMainThread;
    private long startTime;
    private Long endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCallOnMainThread() {
        return callOnMainThread;
    }

    public void setCallOnMainThread(boolean callOnMainThread) {
        this.callOnMainThread = callOnMainThread;
    }

    public boolean isWaitOnMainThread() {
        return waitOnMainThread;
    }

    public void setWaitOnMainThread(boolean waitOnMainThread) {
        this.waitOnMainThread = waitOnMainThread;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
