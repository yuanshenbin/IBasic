package com.yuanshenbin.basic.base

import android.os.Bundle
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2018/7/25
 * desc   :
 */
abstract class BaseMvpActivity<VH : BasicViewHolder, V, P : BasePresenter<V>?> : BaseActivity<VH>() {
    @JvmField
    var mPresenter: P? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = getT<P>(this)
        mPresenter!!.attach(this as V, this)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        mPresenter!!.detach()
        super.onDestroy()
    }

    /**
     * @param o   带泛型对象
     * @param <T> 返回示例类型
     * @return
    </T> */
    private fun <T> getT(o: Any): T? {
        try {
            val a = (o.javaClass
                    .genericSuperclass as ParameterizedType).actualTypeArguments
            return if (a.size == 4) {
                (a[3] as Class<T>).newInstance()
            } else {
                (a[2] as Class<T>).newInstance()
            }
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: ClassCastException) {
            e.printStackTrace()
        }
        return null
    }
}