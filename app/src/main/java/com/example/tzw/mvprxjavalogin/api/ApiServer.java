package com.example.tzw.mvprxjavalogin.api;

import com.example.tzw.mvprxjavalogin.mvp.model.MVPLoginResultBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/9/22.
 */


public interface ApiServer {
    @POST("mine/mbl")
    Call<ResponseBody> getLogin(@Body RequestBody requestBody);

    @POST("mine/mbl")
    Observable<ResponseBody> getRxLogin(@Body RequestBody requestBody);

    @POST("mine/mbl")
    Observable<MVPLoginResultBean> getRxJavaLogin(@Body RequestBody requestBody);
}
