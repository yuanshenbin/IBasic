package com.yuanshenbin.basic;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.yuanshenbin.SoundRecordingManager;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "ysb";

    private List<String> mPermissions = new ArrayList<>();

    private SoundRecordingManager mSoundRecordingManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void getStorePermission() {
        XXPermissions.with(this)
                // 适配 Android 11 分区存储这样写
                //.permission(Permission.Group.STORAGE)
                // 不适配 Android 11 分区存储这样写
                .permission(mPermissions)
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            doMain();
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        showPermissionSettingDialog();
                    }
                });
    }

    private void showPermissionSettingDialog() {
        AlertDialog mPermissionDialog = new AlertDialog.Builder(this)
                .setMessage("已禁用权限，请手动授予")
                .setCancelable(false)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Uri packageURI = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
//                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
//                        startActivity(intent);
                        XXPermissions.startPermissionActivity(MainActivity.this, XXPermissions.getDenied(MainActivity.this, mPermissions));
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //关闭页面或者做其他操作
                        finish();
                    }
                })
                .create();
        mPermissionDialog.show();
    }

    private void doMain() {
        mSoundRecordingManager.start();
    }

}
