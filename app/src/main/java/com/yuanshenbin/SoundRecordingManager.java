package com.yuanshenbin;

import android.media.MediaRecorder;
import android.util.Log;


import com.yuanshenbin.basic.util.FileUtils;
import com.yuanshenbin.basic.util.ToastUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author : admin
 * time   : 2022/11/6
 * desc   :
 */
public class SoundRecordingManager {
    private MediaRecorder mediaRecorder;
    private long time;
    private List<String> mList = new ArrayList<>();
    private int COUNT = 60 * 6;
    private String fileName;
    private static SoundRecordingManager instance;

    public static SoundRecordingManager getInstance() {

        if (instance == null)
            synchronized (SoundRecordingManager.class) {
                instance = new SoundRecordingManager();
            }
        return instance;
    }


    public interface Callback {
        void onResult(String path);
    }


    public void init() {
        fileName="";
        mList.clear();
        time = System.currentTimeMillis();
        File file = new File(FileUtils.getCachePath(App.getContext(), Constants.AUDIO_PATH));
        if (file.exists()) {
            deleteAllFile(file);
        }
        file.mkdirs();
        release();
    }


    public void deleteAllFile(File file) {
        try {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                if (f.isDirectory()) {
                    deleteAllFile(f);
                } else {
                    f.delete();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return FileUtils.getCachePath(App.getContext(), Constants.AUDIO_PATH) + new Random().nextInt(99999999) + System.currentTimeMillis() + ".m4a";
    }

    public String getUploadPath() {
        return fileName;
    }

    public void start() {
        try {
            fileName = getFileName();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // 选择amr格式
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setOutputFile(fileName);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            // 准备好开始录音
            mediaRecorder.prepare();
            mediaRecorder.start();
            ToastUtils.shortToast(App.getContext(), "录音开始");

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ToastUtils.shortToast(App.getContext(), "录制异常" + e.getMessage());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            ToastUtils.shortToast(App.getContext(), "录制异常" + e.getMessage());
        }

    }

    //暂停录音
    public void pause() {
        try {
            if (mediaRecorder != null) {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder=null;
                //录制的文件路径添加到列表
                mList.add(fileName);
                ToastUtils.shortToast(App.getContext(), "录音暂停");
            }

        } catch (Exception e) {
            Log.e("Exception", e.getMessage(), e);
            ToastUtils.shortToast(App.getContext(), "录制异常");
        }
    }

    public void release() {
        try {
            if (mediaRecorder != null) {
                mediaRecorder.release();
                mediaRecorder=null;
            }
        }catch (Exception e){

        }
    }

}
