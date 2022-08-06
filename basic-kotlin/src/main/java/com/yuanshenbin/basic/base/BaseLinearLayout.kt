package com.yuanshenbin.basic.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.yuanshenbin.basic.util.ToastUtils.Companion.shortToast
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2018/6/14
 * desc   :
 */
abstract class BaseLinearLayout<VH : BasicViewHolder?>(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    @JvmField
     var mContext: Context? = null
    @JvmField
     var mVH: VH? = null
     open val isFlag: Boolean
         get() = true

    /**
     * 布局id
     *
     * @return
     */
     abstract fun initLayoutId(): Int
     fun initViewHolder(): VH? {
        return mVH
    }

    /**
     * 初始化默认数据
     * xxx = new xxx();
     */
  protected open fun initViews() {
        mContext = context
        LayoutInflater.from(context).inflate(initLayoutId(), this, true)
        mVH = initViewHolder()
        if (mVH == null) {
            try {
                val data = (this.javaClass
                        .genericSuperclass as ParameterizedType).actualTypeArguments
                val c = Class.forName((data[0] as Class<*>).name).getConstructor(ViewGroup::class.java)
                mVH = c.newInstance(this) as VH
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: NoSuchMethodException) {
                e.printStackTrace()
            } catch (e: InvocationTargetException) {
                e.printStackTrace()
            } finally {
            }
        }
        initAdapter()
        initDatas()
        initEvents()
    }

    /**
     * 初始化默认数据
     * xxx = new xxx();
     */
     abstract fun initDatas()

    /**
     * 初始化监听事件
     */
     abstract fun initEvents()

    /**
     * 初始化适配器
     */
     fun initAdapter() {}
     fun initAttributeSet(attrs: AttributeSet?) {}
     fun IShowToast(str: String?) {
        shortToast(mContext!!, str)
    }

    /**
     * 不带参数跳转
     */
     fun IStartActivity(sla: Class<*>?) {
        mContext!!.startActivity(Intent(mContext, sla))
    }

    /**
     * 带参数跳转
     */
     fun IStartActivity(bundle: Bundle?, sla: Class<*>?) {
        val intent = Intent(mContext, sla)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        mContext!!.startActivity(intent)
    }

    init {
        initAttributeSet(attrs)
        if (isFlag) {
            initViews()
        }
    }
}