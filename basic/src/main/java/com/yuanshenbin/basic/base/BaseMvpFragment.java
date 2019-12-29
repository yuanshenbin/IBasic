package com.yuanshenbin.basic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : yuanshenbin
 * time   : 2018/7/25
 * desc   :
 */

public abstract class BaseMvpFragment<VH extends BasicViewHolder, V, P extends BasePresenter<V>> extends BaseFragment<VH> {
    protected P mPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = getT(this);
        mPresenter.attach((V) this, this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        mPresenter.detach();
        super.onDestroyView();
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
        } catch (Fragment.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
