package com.yuanshenbin.basic.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2018/6/14
 * desc   :
 */
abstract class BaseMvpLinearLayout<VH : BasicViewHolder?, V, P : BasicPresenter<V>?>(context: Context?, attrs: AttributeSet?) : BaseLinearLayout<VH>(context, attrs) {

    @JvmField
    protected var mPresenter: P
    override val isFlag: Boolean
        get() = false

    override fun initViews() {
        mContext = context
        LayoutInflater.from(context).inflate(initLayoutId(), this, true)
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
        initAdapter()
        initDatas()
        initEvents()
    }

     fun onDestroy() {
        mPresenter!!.detach()
    }

    /**
     * @param o   带泛型对象
     * @param <T> 返回示例类型
     * @return
    </T> */
    private fun <T> getT(o: Any): T? {
        try {
            val data = (o.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments
            return (data[2] as Class<T>).newInstance()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
        return null
    }

    init {
        mPresenter = getT(this)!!
        mPresenter!!.attach(this as V, this)
        initViews()
    }
}