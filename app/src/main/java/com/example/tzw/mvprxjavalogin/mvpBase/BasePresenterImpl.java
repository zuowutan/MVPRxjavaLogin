package com.example.tzw.mvprxjavalogin.mvpBase;


/**
 * Created by tzw on 2017/9/21.
 * BasePresenterImpl 是逻辑操作的基类
 * 在这里实现网络请求等耗时的操作，
 * 将逻辑处理的结果（成功or失败or网络请求失败等等V层想要展示的状态）回调给V层
 *
 * @param <T>
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected T mPresenterView ;

    @Override
    public void attachView(T t) {
        this.mPresenterView = t ;
    }

    @Override
    public void detachView() {
        this.mPresenterView = null ;
    }
}
