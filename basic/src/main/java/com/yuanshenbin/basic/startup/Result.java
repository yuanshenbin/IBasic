package com.yuanshenbin.basic.startup;

/**
 * author : yuanshenbin
 * time   : 2023/1/4
 * desc   : https://ke.qq.com/course/4001704#term_id=104153510
 */
public  class Result<T> {

    public T data;

    public  Result(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
