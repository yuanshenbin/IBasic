package com.yuanshenbin.basic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.reflect.ParameterizedType

/**
 * author : yuanshenbin
 * time   : 2018/7/25
 * desc   :
 */
abstract class BaseMvpFragment<VH : BasicViewHolder?, V, P : BasePresenter<V>?> : BaseFragment<VH>() {
    @JvmField
    protected var mPresenter: P? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresenter = getT<P>(this)
        mPresenter!!.attach(this as V, this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        mPresenter!!.detach()
        super.onDestroyView()
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
        } catch (e: java.lang.InstantiationException) {
            e.printStackTrace()
        }
        return null
    }
}