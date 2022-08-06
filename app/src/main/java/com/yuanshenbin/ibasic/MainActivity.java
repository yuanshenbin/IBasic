package com.yuanshenbin.ibasic;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yuanshenbin.Util2;
import com.yuanshenbin.basic.call.Callback;
import com.yuanshenbin.basic.dialog.TipsDialog;
import com.yuanshenbin.basic.imgloader.ImageLoader;
import com.yuanshenbin.basic.util.DateUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.title)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Toast.makeText(MainActivity.this, "" + Color.parseColor("#00000000"), Toast.LENGTH_SHORT).show();


                    }
                });

        String name1 = Util2.getName1();

        Object o = new Object();


        DateUtils.getMillisToString(0, DateUtils.Type.yyyy_MM_dd_SPACE_HH_mm_ss);


        new TipsDialog.Builder(this)
                .callback(new Callback<Object>() {
                    @Override
                    public void ok(Object o) {

                    }
                });

//            ImageLoader.Companion.getInstance()



    }
}
