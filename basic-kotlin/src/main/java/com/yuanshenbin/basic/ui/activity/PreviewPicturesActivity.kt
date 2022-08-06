package com.yuanshenbin.basic.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.github.chrisbanes.photoview.PhotoView
import com.yuanshenbin.basic.R
import com.yuanshenbin.basic.base.BaseActivity
import com.yuanshenbin.basic.base.BasicViewHolder
import com.yuanshenbin.basic.delegate.BaseActivityDelegate
import com.yuanshenbin.basic.imgloader.ImageLoader
import com.yuanshenbin.basic.model.PreviewImgModel
import com.yuanshenbin.basic.ui.vh.PreviewPicturesVH
import com.yuanshenbin.basic.util.DisplayUtils
import kotlinx.android.synthetic.main.base_activity_preview_pictures.*

/**
 * author : yuanshenbin
 * time   : 2021-11-08
 * desc   : 预览大图
 */
class PreviewPicturesActivity : BaseActivity<PreviewPicturesVH>() {
    private var query_pictures: PreviewImgModel? = null

    companion object {
        const val RESULT_PICTURES = "pictures"
    }


    override fun initDelegate(): BaseActivityDelegate? {
        return null
    }

    override fun initIntentExtras(bundle: Bundle?) {
        super.initIntentExtras(bundle)
        mContext
        query_pictures = bundle?.getSerializable(RESULT_PICTURES) as PreviewImgModel?
    }

    override fun initLayoutId(): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //5.0 全透明实现
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }
        return R.layout.base_activity_preview_pictures
    }

    override fun initDatas() {


        val rl = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DisplayUtils.dip2px(this, 44f))
        rl.setMargins(0, DisplayUtils.getStatusBarHeight(mContext!!), 0, 0)
        rl.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        rl_top.layoutParams = rl


        view_pager.setAdapter(SamplePagerAdapter())
        view_pager.setCurrentItem(query_pictures!!.index)
        view_pager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                changeTitle(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        changeTitle(query_pictures!!.index)
    }

    override fun initEvents() {
        ll_back.setOnClickListener { finish() }
    }

    inner class SamplePagerAdapter : PagerAdapter() {
        override fun finishUpdate(container: ViewGroup) {
            try {
                super.finishUpdate(container)
            } catch (nullPointerException: NullPointerException) {
                Log.d("BigImagePagerActivity", "Catch the NullPointerException in FragmentPagerAdapter.finishUpdate")
            }
        }

        override fun getCount(): Int {
            return query_pictures!!.path!!.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            val bigImageView = PhotoView(container.context, null)
            container.addView(bigImageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            ImageLoader.instance.displayImage(query_pictures!!.path!![position], bigImageView)
            return bigImageView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }

    private fun changeTitle(position: Int) {
        tv_title.setText(String.format("%s/%s", position + 1, query_pictures!!.path!!.size))
    }


}