package com.yuanshenbin.basic;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yuanshenbin.ImageConfigTest;
import com.yuanshenbin.TestVH;
import com.yuanshenbin.basic.base.BaseActivity;
import com.yuanshenbin.basic.call.Callback;
import com.yuanshenbin.basic.delegate.BaseActivityDelegate;
import com.yuanshenbin.basic.dialog.TipsDialog;
import com.yuanshenbin.basic.imgloader.IImageLoaderProxy;
import com.yuanshenbin.basic.imgloader.ImageLoader;
import com.yuanshenbin.basic.imgloader.ImageOptions;
import com.yuanshenbin.basic.manager.PreviewManager;
import com.yuanshenbin.basic.model.PreviewImgModel;

import org.jetbrains.annotations.Nullable;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends BaseActivity<TestVH> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main)


        new TipsDialog.Builder(this)
                .content("")
                .callback(new Callback<Object>() {
                    @Override
                    public void ok(Object s) {

                        if(s instanceof PreviewImgModel){

                            PreviewImgModel aa = (PreviewImgModel) s;
                        }

                    }
                }).build();

        new PreviewManager.Builder(this)
                .add("")
                .build();

    }

    @Nullable
    @Override
    public BaseActivityDelegate initDelegate() {
        return null;
    }

    @Override
    public int initLayoutId() {
        return 0;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initEvents() {

    }
}
