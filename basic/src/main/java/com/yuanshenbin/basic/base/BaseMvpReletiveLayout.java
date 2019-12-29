package com.yuanshenbin.basic.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : yuanshenbin
 * time   : 2018/6/14
 * desc   :
 */
public abstract class BaseMvpReletiveLayout<VH extends BasicViewHolder, V, P extends BasicPresenter<V>> extends BaseReletiveLayout<VH> {
    protected P mPresenter;

    @Override
    public boolean isFlag() {
        return false;
    }

    protected void initViews() {
        mContext = getContext();
        LayoutInflater.from(getContext()).inflate(initLayoutId(), this, true);
        try {

            Type[] data = ((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments();
            Constructor c = Class.forName(((Class) data[0]).getName()).getConstructor(ViewGroup.class);
            mVH = (VH) c.newInstance(this);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {

        }
        initAdapter();
        initDatas();
        initEvents();

    }


    public BaseMvpReletiveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPresenter = getT(this);
        mPresenter.attach((V) this, this);
        initViews();
    }


    protected void onDestroy() {
        mPresenter.detach();
    }


    /**
     * @param o   带泛型对象
     * @param <T> 返回示例类型
     * @return
     */
    public static <T> T getT(Object o) {
        try {

            Type[] data = ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments();

            return ((Class<T>) data[2]).newInstance();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
