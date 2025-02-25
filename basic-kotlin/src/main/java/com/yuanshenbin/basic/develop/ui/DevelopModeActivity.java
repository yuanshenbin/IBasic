package com.yuanshenbin.basic.develop.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.develop.DevelopMode;

import io.reactivex.annotations.Nullable;


/**
 * author : yuanshenbin
 * time   : 2018/8/29
 * desc   :
 */

public class DevelopModeActivity extends DevelopBaseActivity {
    private ViewHolder mVH;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_develop_activity_develop_mode);
        mVH = new ViewHolder(this);
        mVH.tv_title.setText("开发者模式");
        mVH.tv_api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevelopModeActivity.this, SystemApiActivity.class));
            }
        });
        mVH.tv_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DevelopModeActivity.this, LogListActivity.class));
            }
        });
        mVH.tv_catch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DevelopModeActivity.this, ErrorDescActivity.class);
                intent.putExtra(ErrorDescActivity.RESULT_ERROR_LIST,true);
                startActivity(intent);
            }
        });
        mVH.layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (DevelopMode.getInstance().getDevelopConfig()!=null&&DevelopMode.getInstance().getDevelopConfig().getRootListener() != null) {
            mVH.sv_scroll.setVisibility(View.VISIBLE);
            mVH.ll_custom_root.removeAllViews();
            DevelopMode.getInstance().getDevelopConfig().getRootListener().onCustomRoot(mVH.ll_custom_root);
        } else {
            mVH.sv_scroll.setVisibility(View.GONE);
        }

    }

    public static class ViewHolder {
        public ImageView layout_back;
        public TextView tv_title;
        public TextView tv_api;
        public TextView tv_log;
        public TextView tv_catch;
        public LinearLayout ll_custom_root;
        public ScrollView sv_scroll;

        public ViewHolder(Activity rootView) {
            this.layout_back = (ImageView) rootView.findViewById(R.id.layout_back);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_catch = (TextView) rootView.findViewById(R.id.tv_catch);
            this.tv_api = (TextView) rootView.findViewById(R.id.tv_api);
            this.tv_log = (TextView) rootView.findViewById(R.id.tv_log);
            this.ll_custom_root = (LinearLayout) rootView.findViewById(R.id.ll_custom_root);
            this.sv_scroll = (ScrollView) rootView.findViewById(R.id.sv_scroll);
        }

    }

}
