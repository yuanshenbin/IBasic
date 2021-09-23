package com.yuanshenbin.basic.base;

import android.os.Bundle;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.Nullable;

/**
 * author : yuanshenbin
 * time   : 2018/7/25
 * desc   :
 */

public abstract class BaseMvpActivity<VH extends BasicViewHolder, V, P extends BasePresenter<V>> extends BaseActivity<VH> {
    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = getT(this);
        mPresenter.attach((V) this, this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    /**
     * @param o   带泛型对象
     * @param <T> 返回示例类型
     * @return
     */
    public static <T> T getT(Object o) {
        try {
            Type[] a = ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments();
            if (a.length == 4) {
                return ((Class<T>) (a[3])).newInstance();
            } else {
                return ((Class<T>) (a[2])).newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

}
