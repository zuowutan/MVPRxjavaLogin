package com.example.tzw.mvprxjavalogin.mvpBase;


/**
 * Created by tzw on 2017/9/21.
 * P层接口的基类：
 * 绑定V层接口
 * 解绑V层接口
 * @param <T>
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T t);
    void detachView();
}
