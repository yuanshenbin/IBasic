package com.yuanshenbin.basic.dialog

import android.content.Context
import android.os.Handler
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import com.yuanshenbin.basic.R
import com.yuanshenbin.basic.base.BaseDialog
import com.yuanshenbin.basic.base.BasicViewHolder
import com.yuanshenbin.basic.call.Callback
import com.yuanshenbin.basic.call.CustomDialogListener
import com.yuanshenbin.basic.config.BasicOptions

import com.yuanshenbin.basic.util.Utils
import kotlinx.android.synthetic.main.basic_dialog_tips.*
import kotlinx.android.synthetic.main.basic_dialog_tips_bottom_styles.*

/**
 * author : yuanshenbin
 * time   : 2021-02-14
 * desc   : 标准提示框
 */
class TipsDialog(private val mBuilder: Builder) : BaseDialog<BasicViewHolder, Any>(mBuilder.context) {
    override fun initConfig() {
        Handler().post {
            delayInitConfig()
            show()
        }
    }

    override fun initLayoutId(): Int {
        return R.layout.basic_dialog_tips
    }

    override fun initDatas() {
        if (mBuilder.single) {
            tv_cancel.visibility = View.GONE
        }
        if (!Utils.isEmpty(mBuilder.title)) {
            tv_title.text = mBuilder.title
        } else {
            tv_title.visibility = View.GONE
        }
        if (mBuilder.listener != null && mBuilder.resId != 0) {
            val view = LayoutInflater.from(mActivity).inflate(mBuilder.resId, null)
            ll_content.removeAllViews()
            ll_content.addView(view)
            mBuilder.listener?.onCustomLayout(view, this)
        } else {
            if (!Utils.isEmpty(mBuilder.content)) {
                tv_content.text = mBuilder.content
                if (mBuilder.content?.length!! > 150) {
                    tv_content.movementMethod = ScrollingMovementMethod.getInstance()
                }
            }
        }
        tv_content.gravity = mBuilder.contentGravity
        tv_title.gravity = mBuilder.titleGravity
        tv_ok.text = mBuilder.okContent
        tv_cancel.text = mBuilder.cancelContent
        tv_cancel.setTextColor(mBuilder.cancelBtnColor)
        tv_ok.setTextColor(mBuilder.okBtnColor)
        setCanceledOnTouchOutside(mBuilder.cancelable)
            setCancelable(mBuilder.cancelable)


    }

    override fun initEvents() {
        tv_ok.setOnClickListener {
            if (mBuilder.listener != null && mBuilder.resId != 0) {
                mBuilder.listener?.onOk(mBuilder.callback)
            } else {
                mBuilder?.callback?.ok("")
                dismiss()
            }
        }

        tv_cancel.setOnClickListener {
            if (mBuilder.listener != null && mBuilder.resId != 0) {
                mBuilder.listener?.onCancel(mBuilder.callback)
            } else {

                mBuilder?.callback?.cancel("")
                dismiss()
            }
        }
    }


    class Builder(internal var context: Context,
                  internal var title: String = BasicOptions.instance.tipsAbstract.title,
                  internal var content //内容
                  : CharSequence? = null,
                  internal var single: Boolean = false,//是单选
                  internal var cancelable: Boolean = true,//控制dialog是否允许返回  true是可以
                  internal var titleGravity: Int = BasicOptions.instance.tipsAbstract.titleGravity,
                  internal var contentGravity: Int = BasicOptions.instance.tipsAbstract.contentGravity,
                  internal var okContent: String = BasicOptions.instance.tipsAbstract.okContentTitle,
                  internal var okBtnColor: Int = BasicOptions.instance.tipsAbstract.okContentTitleColor,
                  internal var cancelContent: String = BasicOptions.instance.tipsAbstract.cancelContentTitle,
                  internal var cancelBtnColor: Int = BasicOptions.instance.tipsAbstract.cancelContentColor,
                  internal var resId: Int = 0,
                  internal var listener: CustomDialogListener<Any>? = null,
                  internal var callback: Callback<Any>? = null
    ) {


        constructor(context: Context) :
                this(context, BasicOptions.instance.tipsAbstract.title,
                        null, false, true,
                        BasicOptions.instance.tipsAbstract.titleGravity, BasicOptions.instance.tipsAbstract.contentGravity,
                        BasicOptions.instance.tipsAbstract.okContentTitle, BasicOptions.instance.tipsAbstract.okContentTitleColor,
                        BasicOptions.instance.tipsAbstract.cancelContentTitle, BasicOptions.instance.tipsAbstract.cancelContentColor,
                        0, null, null) {
            this.context = context
        }


        fun contentGravity(contentGravity: Int): Builder {
            this.contentGravity = contentGravity
            return this
        }

        fun okBtnColor(okBtnColor: Int): Builder {
            this.okBtnColor = okBtnColor
            return this
        }

        fun cancelBtnColor(cancelBtnColor: Int): Builder {
            this.cancelBtnColor = cancelBtnColor
            return this
        }

        fun okContent(okContent: String): Builder {
            this.okContent = okContent
            return this
        }

        fun cancelContent(cancelContent: String): Builder {
            this.cancelContent = cancelContent
            return this
        }

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun content(content: CharSequence?): Builder {
            this.content = content
            return this
        }

        fun isSingle(single: Boolean): Builder {
            this.single = single
            return this
        }

        fun <T>callback(callback: Callback<T>?): Builder {
            this.callback = callback as Callback<Any>
            return this
        }

        fun isCancelable(flag: Boolean): Builder {
            cancelable = flag
            return this
        }

        fun <T>setLayoutRes(resId: Int, listener: CustomDialogListener<T>?): Builder {
            this.listener = listener as CustomDialogListener<Any>
            this.resId = resId
            return this
        }


        fun build(): TipsDialog {
            return TipsDialog(this)
        }

    }

    override val width: Int
        get() = widthDefault

    override val animations: Int
        get() = R.style.basic_tips_dialog

}