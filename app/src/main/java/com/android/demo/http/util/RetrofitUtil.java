package com.android.demo.http.util;

import android.content.Context;
import android.util.Log;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/2/2/002.
 */

public class RetrofitUtil {

    //服务路径
    private  Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private String TAG ="RetrofitUtil";

    //获取Retrofit对象

    public RetrofitUtil(Context context,String url,String token) {
            Log.d(TAG,"第一次token为null,登录请求 111-222 ");
            mOkHttpClient = new OkHttpUtils().getOkHttpClient(context,token);
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();


    }

    //返回一个泛型
    public <T> T getServer(Class<T> server) {
        return mRetrofit.create(server);
    }

}
