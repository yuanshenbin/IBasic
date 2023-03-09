package com.yuanshenbin.basic.model;


import java.util.ArrayList;
import java.util.List;


public class LogRecorder {
    public static LogRecorder instance;
    private List<LogModel> logModels = new ArrayList<>();

    public static synchronized LogRecorder getInstance(){
        if (instance == null){
            instance = new LogRecorder();
        }
        return instance;
    }

    public void addLog(LogModel logModel){
        if (logModels.size()>50){
            logModels.remove(0);
        }
        logModels.add(logModel);
    }


    public List<LogModel> getLogModels() {
        return logModels;
    }

    public void setLogModels(List<LogModel> logModels) {
        this.logModels = logModels;
    }
}
