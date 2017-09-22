package com.example.tzw.mvprxjavalogin.mvp.prensenter;

import com.example.tzw.mvprxjavalogin.mvp.model.MVPLoginBean;
import com.example.tzw.mvprxjavalogin.mvp.view.LoginView;
import com.example.tzw.mvprxjavalogin.mvpBase.BasePresenter;

/**
 * Created by tzw on 2017/9/21.
 *   这里就是做数据逻辑的接口  因为，在P层的实现类中，我们需要将账号密码放在请求体中
 *   所以，需要将V层的MVPLoginBean 对象传过来
 */
public interface LoginPresenter extends BasePresenter<LoginView> {
    void   mvpLoginMethod(MVPLoginBean bean);
}
