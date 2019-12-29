package com.yuanshenbin.basic.base;

import android.content.Context;
import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author : yuanshenbin
 * time   : 2018/9/26
 * desc   :
 */

public abstract class BaseMvpDialog<VH extends BasicViewHolder, V, P extends BasicPresenter<V>, Call>
        extends BaseDialog<VH, Call> {
    protected P mPresenter;

    @Override
    protected boolean isFlag() {
        return false;
    }

    public BaseMvpDialog(@NonNull Context context) {
        super(context);
        initConfig();
        mPresenter = getT(this);
        mPresenter.attach((V) this, this);
    }

    /**
     * @param o   带泛型对象
     * @param <T> 返回示例类型
     * @return
     */
    private static <T> T getT(Object o) {
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
    public  void  onDestroyDialog(){
        mPresenter.detach();
    }
}
