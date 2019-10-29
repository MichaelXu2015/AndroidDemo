package com.android.demo.http.util;

import android.content.Context;
import android.util.Log;

import com.android.demo.http.sp.SpUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Administrator on 2018/2/2/002.
 */

public class OkHttpUtils {

    private OkHttpClient mOkHttpClient;
    private String TAG = "OkHttpUtils";

    //设置缓存目录
    private File cacheDirectory;

    private Cache cache;
    private String mToken = "";
    private SpUtil spUtil;


    public OkHttpClient getOkHttpClient(Context context,String token) {
            initCachedir(context);
            mToken = token;
            Log.d(TAG,"token == "+token);
            mOkHttpClient = new OkHttpClient.Builder()
                    .cookieJar(CookieJar.NO_COOKIES)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(responseInterceptor)
                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.d(TAG,"http message : "+message);

                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))
                    .cache(cache)
                    .build();
        //}
        return mOkHttpClient;
    }

    /**
     * 初始化缓存目录
     * @param context
     */
    private  void initCachedir(Context context){
        cacheDirectory = new File(context.getApplicationContext().getCacheDir().getAbsolutePath(), "httpCache");
        cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
    }

    //请求拦截
    private Interceptor requestInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            return null;
        }
    };

    //响应拦截
    private Interceptor responseInterceptor = new Interceptor() {




        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("token",mToken)
                    .build();
            return chain.proceed(request);
        }
    };
}
