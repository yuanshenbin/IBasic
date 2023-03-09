package com.yuanshenbin.basic.manager

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import com.yuanshenbin.basic.model.PreviewImgModel
import com.yuanshenbin.basic.ui.activity.PreviewPicturesActivity

/**
 * author : yuanshenbin
 * time   : 2021-11-08
 * desc   :
 */
class PreviewManager(builder: Builder) {
    class Builder(internal var context: Context,
                  internal var index: Int = 0,
                  internal var orientation:Int = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,
                  internal var urls: MutableList<String>? = ArrayList()
    ) {

        constructor(context: Context) : this(context, 0, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT,ArrayList()) {
            this.context = context
        }


        fun index(index: Int): Builder {
            this.index = index
            return this
        }
        fun orientation(orientation: Int): Builder {
            this.orientation = orientation
            return this
        }
        fun add(url: String?): Builder {
            urls!!.add(url!!)
            return this
        }

        fun addAll(urls: MutableList<String>): Builder {
            this.urls!!.addAll(urls)
            return this
        }

        fun build(): PreviewManager {
            return PreviewManager(this)
        }

    }

    init {
        val model = PreviewImgModel()
        model.index = builder.index
        model.path = builder.urls
        model.orientation = builder.orientation
        val intent = Intent(builder.context, PreviewPicturesActivity::class.java)
        intent.putExtra(PreviewPicturesActivity.RESULT_PICTURES, model)
        builder.context.startActivity(intent)
    }
}