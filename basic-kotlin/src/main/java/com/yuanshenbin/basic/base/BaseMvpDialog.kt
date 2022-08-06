package com.yuanshenbin.basic.base

import android.content.Context
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2018/9/26
 * desc   :
 */
abstract class BaseMvpDialog<VH : BasicViewHolder?, V, P : BasicPresenter<V>?, Call>(context: Context) : BaseDialog<VH, Call>(context) {
    protected var mPresenter: P
    override val isFlag: Boolean
        get() = false

    /**
     * @param <T> 返回示例类型
     * @return
    </T> */
    private fun <T> getT(): T? {
        try {
            val data = (this@BaseMvpDialog.javaClass
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

    fun onDestroyDialog() {
        mPresenter!!.detach()
    }

    init {
        initConfig()
        mPresenter = getT()!!
        mPresenter!!.attach(this@BaseMvpDialog as V, this@BaseMvpDialog)
    }
}