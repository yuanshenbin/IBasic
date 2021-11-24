package com.yuanshenbin.basic.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.github.chrisbanes.photoview.PhotoView;
import com.yuanshenbin.basic.R;
import com.yuanshenbin.basic.base.BaseActivity;
import com.yuanshenbin.basic.delegate.BaseActivityDelegate;
import com.yuanshenbin.basic.imgloader.ImageLoader;
import com.yuanshenbin.basic.model.PreviewImgModel;
import com.yuanshenbin.basic.ui.vh.PreviewPicturesVH;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * author : yuanshenbin
 * time   : 2021-11-08
 * desc   : 预览大图
 */
public class PreviewPicturesActivity extends BaseActivity<PreviewPicturesVH> {
    public static final String RESULT_PICTURES = "pictures";
    private PreviewImgModel query_pictures;

    @Override
    protected BaseActivityDelegate initDelegate() {
        return null;
    }

    @Override
    protected void initIntentExtras(Bundle bundle) {
        super.initIntentExtras(bundle);
        query_pictures = (PreviewImgModel) bundle.getSerializable(RESULT_PICTURES);
    }

    @Override
    protected int initLayoutId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//5.0 全透明实现
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        return R.layout.base_activity_preview_pictures;
    }


    @Override
    protected void initDatas() {
        mVH.view_pager.setAdapter(new SamplePagerAdapter());
        mVH.view_pager.setCurrentItem(query_pictures.getIndex());
        mVH.view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        changeTitle(query_pictures.getIndex());
    }

    @Override
    protected void initEvents() {

        mVH.ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class SamplePagerAdapter extends PagerAdapter {


        @Override
        public void finishUpdate(@NonNull ViewGroup container) {
            try {
                super.finishUpdate(container);
            } catch (NullPointerException nullPointerException) {
                Log.d("BigImagePagerActivity", "Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
            }

        }

        @Override
        public int getCount() {
            return query_pictures.getPath().size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            final PhotoView bigImageView = new PhotoView(container.getContext(), null);

            container.addView(bigImageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            ImageLoader.getInstance().displayImage(query_pictures.getPath().get(position), bigImageView);
            return bigImageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }


    private void changeTitle( int position) {
        mVH.tv_title.setText(String.format("%s/%s",  position + 1, query_pictures.getPath().size()));
    }
}
