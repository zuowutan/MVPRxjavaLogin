package com.example.tzw.mvprxjavalogin.mvp.view;

import com.example.tzw.mvprxjavalogin.mvpBase.BaseView;


 /**
 * Created by tzw on 2017/9/21.
 * 这里就是View层需要实现具体的接口
 *
 * V 层主要是 接收到P层的回调信息
 * 在登录的例子中 由于我们只需要知道
 * 登录成功，
 * 登录失败，
 * 登录异常（网络链接错误，服务器宕机，等等）这三种状态即可
 *

 */

public interface LoginView extends BaseView {
    //    这里的回调结果 只是做例子  具体回调的数据 可以根据业务需求拓展
    void loginSuccess(int tag, String msg);

    void loginFailed(int tag, String msg);

    void loginException(String msg);
}
