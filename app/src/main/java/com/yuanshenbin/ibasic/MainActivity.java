package com.yuanshenbin.ibasic;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yuanshenbin.ImageConfigTest;
import com.yuanshenbin.basic.call.Callback;
import com.yuanshenbin.basic.config.BasicOptions;
import com.yuanshenbin.basic.dialog.TipsDialog;
import com.yuanshenbin.basic.imgloader.IImageLoaderProxy;
import com.yuanshenbin.basic.imgloader.ImageLoader;
import com.yuanshenbin.basic.imgloader.ImageOptions;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageLoader.getInstance()
                .setImageLoaderProxy(new IImageLoaderProxy() {

                    @Override
                    public void displayImage(Object url, View view) {
                        if (view instanceof ImageView) {

                        }
                    }

                    @Override
                    public void placeholder(int resId) {

                    }

                    @Override
                    public void options(ImageOptions l) {
                        if (l instanceof ImageConfigTest) {

                        }

                    }
                });



        ImageLoader
                .getInstance()
                .placeholder(0)
                .options(new ImageConfigTest())
                .displayImage("", null);

        findViewById(R.id.title)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {



                        new TipsDialog.Builder(MainActivity.this)
                                .content("大哥，下午好")
                                .okContent("")
                                .callback(new Callback() {
                                    @Override
                                    public void ok(Object o) {

                                    }

                                    @Override
                                    public void cancel(Object o) {

                                    }
                                })
                                .build();


                    }
                });
    }
}
