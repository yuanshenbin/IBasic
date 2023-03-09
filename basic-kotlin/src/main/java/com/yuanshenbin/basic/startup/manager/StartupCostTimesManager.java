package com.yuanshenbin.basic.startup.manager;

import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.log.ILog;
import com.yuanshenbin.basic.startup.Startup;
import com.yuanshenbin.basic.startup.model.CostTimesModel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author : yuanshenbin
 * time   : 2023/1/9
 * desc   :
 */
public class StartupCostTimesManager {

    private ConcurrentHashMap<String, CostTimesModel> costTimesMap = new ConcurrentHashMap<>();
    private static long ACCURACY = 1000 * 1000L;//准确性
    private long startTime = 0L;
    private Long endTime;
    private static StartupCostTimesManager mInstance;

    private StartupCostTimesManager() {
    }

    public static StartupCostTimesManager getInstance() {
        if (mInstance == null) {
            synchronized (StartupCostTimesManager.class) {
                if (mInstance == null) {
                    mInstance = new StartupCostTimesManager();
                }
            }
        }
        return mInstance;
    }

    public long getMainThreadTimes() {
        return (endTime == null ? System.nanoTime() : endTime) - startTime;
    }

    public void recordStart(Startup<?> startup) {
        if (BasicOptions.getInstance().isDebug()) {
            CostTimesModel model = new CostTimesModel();
            model.setName(startup.getClass().getSimpleName());
            model.setCallOnMainThread(startup.callCreateOnMainThread());
            model.setWaitOnMainThread(startup.waitOnMainThread());
            model.setStartTime(System.nanoTime() / ACCURACY);
            costTimesMap.put(startup.getClass().getSimpleName(), model);
        }
    }

    public void recordEnd(Startup<?> startup) {
        if (BasicOptions.getInstance().isDebug()) {
            CostTimesModel model = costTimesMap.get(startup.getClass().getSimpleName());
            if (model != null) {
                model.setEndTime(System.nanoTime() / ACCURACY);
            }
        }

    }

    public void clear() {
        endTime = null;
        startTime = System.nanoTime();
        costTimesMap.clear();
    }

    public void printAll() {
        StringBuilder sb = new StringBuilder();

        long sdkCountTimers = 0;
        if (BasicOptions.getInstance().isDebug()) {
            for (CostTimesModel value : costTimesMap.values()) {
                sb.append("\n");
                sb.append("|      Startup Name       |   " + value.getName() + "");
                sb.append("\n");
                sb.append("| ----------------------- | --------------------------------------");
                sb.append("\n");
                sb.append("|   Call On Main Thread   |   " + value.isCallOnMainThread() + "");
                sb.append("\n");
                sb.append("| ----------------------- | --------------------------------------");
                sb.append("\n");
                sb.append("|   Wait On Main Thread   |    " + value.isWaitOnMainThread() + "");
                sb.append("\n");
                sb.append("| ----------------------- | --------------------------------------");
                sb.append("\n");
                long temp = value.getEndTime() - value.getStartTime();
                sdkCountTimers = sdkCountTimers + temp;
                sb.append("|       Cost Times        |    " + temp + " ms");
                sb.append("\n");
                sb.append("|=================================================================");
            }
            sb.append("\n");
            sb.append("| Total Main Thread Times |  " + (getMainThreadTimes() / ACCURACY) + " ms   | sdk init total times |" + sdkCountTimers + " ms");
            sb.append("\n");
            sb.append("|=================================================================");

            ILog.e("startup", sb.toString());
        }
        clear();
    }
}
