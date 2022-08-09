package com.yuanshenbin.basic.base

import android.app.Activity
import android.app.Dialog
import android.app.Fragment
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.BadTokenException
import androidx.annotation.StyleRes
import com.yuanshenbin.basic.R
import com.yuanshenbin.basic.call.Callback
import com.yuanshenbin.basic.util.ToastUtils
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2018/9/26
 * desc   :
 */
abstract class BaseDialog<VH : BasicViewHolder?, Call> : Dialog {
    @JvmField
    protected var mActivity: Activity? = null
    @JvmField
    protected var mVH: VH? = null

    protected fun initViewHolder(): VH? {
        return mVH
    }

    open val isFlag: Boolean
        get() = true

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, @StyleRes themeResId: Int) : super(context, themeResId) {
        init(context)
    }

    private fun init(context: Context) {
        mActivity = context as Activity
        try {
            val win = this.window
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            win!!.setGravity(gravity)
            win.setBackgroundDrawableResource(R.color.basic_color_transparent)
            val lp = win.attributes
            win.decorView.setPadding(0, 0, 0, 0)
            lp.width = width
            lp.height = height
            lp.dimAmount = dimAmount
            lp.alpha = alpha
            win.attributes = lp
            setCancelable(cancelable)
            setCanceledOnTouchOutside(getCanceledOnTouchOutside(true))
            win.setContentView(initLayoutId())
            if (animations != 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                    win.setWindowAnimations(animations)
                }
            }
            initViews(win)
            if (isFlag) {
                initConfig()
            }
            setOnDismissListener {
                callback?.close()
            }
        } catch (exception: BadTokenException) {
            exception.printStackTrace()
        }
    }

    protected open fun initConfig() {
        mVH = initViewHolder()
        if (mVH == null) {
            try {
                val data = (this.javaClass
                        .genericSuperclass as ParameterizedType).actualTypeArguments
                val c = Class.forName((data[0] as Class<*>).name).getConstructor(Window::class.java)
                mVH = c.newInstance(rootView) as VH
            } catch (e: Fragment.InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } finally {
            }
        }
        initAdapter()
        initDatas()
        initEvents()
        setOnDismissListener {
            callback?.close()
        }
    }

    protected fun delayInitConfig() {
        try {
            val data = (this.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments
            val c = Class.forName((data[0] as Class<*>).name).getConstructor(Window::class.java)
            mVH = c.newInstance(window) as VH
        } catch (e: Fragment.InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } finally {
        }
        initDatas()
        initEvents()
        initAdapter()
    }

    protected abstract open fun initLayoutId(): Int

    /**
     * 初始化默认数据
     * xxx = new xxx();
     */
    protected abstract open fun initDatas()

    /**
     * 初始化监听事件
     */
    protected abstract open fun initEvents()

    /**
     * 初始化适配器
     */
    protected open fun initAdapter() {}
    protected  open  fun initViews(win: Window?) {}

    fun IShowToast(str: String?) {
        ToastUtils.shortToast(mActivity!!, str)
    }

    /**
     * 获得根view
     *
     * @return
     */
    open val rootView: Window?
        get() = window


    open val dimAmount: Float
        get() = 0.6f

    open val width: Int
        get() = WindowManager.LayoutParams.MATCH_PARENT

    open val widthDefault: Int
        get() = (widthPixels * 0.85).toInt()

    open val widthPixels: Int
        get() {
            val metric = DisplayMetrics()
            mActivity!!.windowManager.defaultDisplay.getMetrics(metric)
            return metric.widthPixels
        }

    open val alpha: Float
        get() = 1.0f

    open val gravity: Int
        get() = Gravity.CENTER

    open val height: Int
        get() = WindowManager.LayoutParams.WRAP_CONTENT

    open fun getCanceledOnTouchOutside(cancel: Boolean): Boolean {
        return cancel
    }


    open val cancelable: Boolean
        get() = true

    open val animations: Int
        get() = 0

    override fun show() {
        if (mActivity != null && !mActivity!!.isFinishing && !this.isShowing) {
            super.show()
        }
    }

    override fun dismiss() {
        if (mActivity == null || !this.isShowing) return
        super.dismiss()
    }

    var callback: Callback<Call>? = null


    fun callback(callback: Callback<Call>): BaseDialog<VH, Call> {
        this.callback = callback
        return this
    }
}