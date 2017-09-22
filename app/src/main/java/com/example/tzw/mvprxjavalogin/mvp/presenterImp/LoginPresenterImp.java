package com.example.tzw.mvprxjavalogin.mvp.presenterImp;

import com.example.tzw.mvprxjavalogin.api.ApiServer;
import com.example.tzw.mvprxjavalogin.mvp.model.MVPLoginBean;
import com.example.tzw.mvprxjavalogin.mvp.model.MVPLoginResultBean;
import com.example.tzw.mvprxjavalogin.mvp.prensenter.LoginPresenter;
import com.example.tzw.mvprxjavalogin.mvp.view.LoginView;
import com.example.tzw.mvprxjavalogin.mvpBase.BasePresenterImpl;
import com.example.tzw.mvprxjavalogin.tools.CommonConfig;
import com.example.tzw.mvprxjavalogin.tools.MD5Tool;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/9/22.
 */

public class LoginPresenterImp extends BasePresenterImpl<LoginView> implements LoginPresenter {
    private final String TAG = "MainActivity";
    private String userName;
    private String passWord;
    private String requestParams;
    private RequestBody requestBody;
    private LoginView mLoginView;
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

    @Override
    public void attachView(LoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void mvpLoginMethod(MVPLoginBean user) {
//       V层拿到的具体参数
        userName = user.getUserName();
        passWord = user.getPassWord();

//      构建OkHttpClient  OkHttpClient建议写成全局单例
        OkHttpClient mOkHttpClient = new OkHttpClient();


//      请求体
        requestBody = RequestBody.create(JSON, createRequestPrams(userName, passWord));

//        构建Retrofit 这里可以拓展OkhttpClient  设置一些读写超时配置 根据自己的业务需求使用
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(CommonConfig.httpLoginUrl).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();

//        发起请求 解析数据 将结果回调给V层
        ApiServer apiServer = retrofit.create(ApiServer.class);
        apiServer.getRxJavaLogin(requestBody).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<MVPLoginResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MVPLoginResultBean value) {
//                        这里是返回的数据源 自行拓展业务
                        String status = value.getStatus();
                        if ("YHDL_000".equals(status)) {
                            mLoginView.loginSuccess(0, "登录成功");
                        } else {
                            mLoginView.loginFailed(0, "帐号密码错误");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoginView.loginException("网络异常");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


/*
Rxjava+retofit 直接返回MVPLoginResultBean    这种写法也行:
apiServer.getRxJavaLogin(requestBody).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MVPLoginResultBean>() {
            @Override
            public void accept(MVPLoginResultBean mvpLoginResultBean) throws Exception {
                try {
                    if (mvpLoginResultBean != null){
                        String sid = mvpLoginResultBean.getSid();
                        String msg = mvpLoginResultBean.getMsg();
                        Logger.i(TAG,"Rxjava callback:"+msg);
                        mLoginView.loginSuccess(0,"Rxjava"+msg);
                    }else {
                        mLoginView.loginFailed(0,"登录异常");
                    }

                }catch (Exception e){
                    mLoginView.loginException("Rxjava 回调登录异常");
                }

            }
        });*/


/*
Rxjava+retofit 直接返回ResponseBody   这种写法也行:
apiServer.getRxLogin(requestBody).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        try {
                            if (responseBody != null && responseBody.contentLength()>0){
                                JSONObject j = new JSONObject(responseBody.string());
                                Logger.i(TAG,"RxJava Login ResponseBody :"+ j);
                                String status =  j.optString("status");
                                if ("YHDL_000".equals(status)){
                                    mLoginView.loginSuccess(0,"RxJava 回调登录成功");
                                }else {
                                    mLoginView.loginFailed(0,"Rxjava 回调登录失败");
                                }
                            }else {
                                mLoginView.loginException("Rxjava 回调登录异常");
                            }
                        }catch (Exception e){
                            mLoginView.loginException("Rxjava 回调登录异常");
                        }

                    }
                });*/

//  这种写法也可以   数据源:ResponseBody
// 打开注释就可以(建议使用这种)
      /*  apiServer.getRxLogin(requestBody).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ResponseBody value) {
                        try {
                            Logger.i(TAG,"RxJava Login next ResponseBody:"+value.string());
                            JSONObject js = new JSONObject(value.string().trim());
                            final String status = js.optString("status");
                            if ("YHDL_000".equals(status)){
                                mLoginView.loginSuccess(0,"RxJava回调登录成功");
                            }else {
                                mLoginView.loginFailed(0,"Rxjava回调登录失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Logger.i(TAG,"RxJava Login error ResponseBody:"+"登录异常");
                        mLoginView.loginException("Rxjava回调登录异常");
                    }
                    @Override
                    public void onComplete() {
                    }
                });*/




// 传统Retrofit写法:
// 这个为了演示  这是可以正常使用的,只是熟悉API 打开注释即可使用
/*      Retrofit retrofit = new Retrofit.Builder().baseUrl(CommonConfig.httpLoginUrl).build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
//      返回的Call对象
        Call<ResponseBody> login = apiServer.getLogin(requestBody);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseBody =  response.body().string();
                    Logger.i(TAG,"Retrofit ResponseBody :"+  responseBody);
                    JSONObject js = new JSONObject(responseBody);
                    String status = js.optString("status");
                    if ("YHDL_000".equals(status)){
                        mLoginView.loginSuccess(0,"回调登录成功");
                    }else {
                        mLoginView.loginFailed(0,"回调登录失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mLoginView.loginException("回调登录异常");
            }
        });*/



//最经典的OkHttp写法:
//       构建Request请求:
/*
        Request request = new Request.Builder().url(CommonConfig.httpOkhttpLoginUrl).post(requestBody).build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
            @Override
            public void onFailure(Call call, IOException e) {

            }

        });
*/



    }

    public String createRequestPrams(String userName, String passWord) {
        StringBuilder preSign = new StringBuilder();
        preSign.append("appId=").append(CommonConfig.APP_ID);
        preSign.append("&type=").append(CommonConfig.TYPE);
        preSign.append("&account=").append(userName);
        preSign.append("&password=").append(passWord);
        preSign.append("&ext=").append("");
        preSign.append("&version=").append(CommonConfig.VERSION);
        preSign.append("&ip=").append(CommonConfig.ip);
        preSign.append("&mac=").append(CommonConfig.mac);
        preSign.append("&imei=").append(CommonConfig.imei);
        preSign.append("&channel=").append(CommonConfig.CHANNEL);
        preSign.append("||").append(CommonConfig.APP_KEY);
        String sign = MD5Tool.calcMD5(preSign.toString().getBytes());
        try {
            JSONObject param = new JSONObject();
            param.put("appId", CommonConfig.APP_ID);
            param.put("type", CommonConfig.TYPE);
            param.put("account", userName);
            param.put("password", passWord);
            param.put("ext", "");
            param.put("version", CommonConfig.VERSION);
            param.put("ip", CommonConfig.ip);
            param.put("mac", CommonConfig.mac);
            param.put("imei", CommonConfig.imei);
            param.put("channel", CommonConfig.CHANNEL);
            param.put("platformType", CommonConfig.PLATFORM_TYPE);
            param.put("sign", sign);
            requestParams = param.toString();
        } catch (Exception e) {
            mLoginView.loginException("参数拼接异常");
        }
        return requestParams;
    }

    @Override
    public void detachView() {
        mLoginView = null;
    }
}
