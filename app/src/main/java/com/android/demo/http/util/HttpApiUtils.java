package com.android.demo.http.util;

import android.content.Context;

import com.android.demo.http.api.ShopMallApi;
import com.android.demo.http.sp.SpUtil;


public class HttpApiUtils {

    private static RetrofitUtil mRetrofitUtil;
    private static final String URL = "http://192.168.50.107:20000";//zuul网关地址
   // private static final String URL = "http://192.168.50.106:8004";
    private static String TAG = "HttpApiUtils";

    private static <T> T getApi(Context context,Class<T> server) {
        String token = SpUtil.getSpUtil(context).getString(SpUtil.TOKEN);
        mRetrofitUtil = new RetrofitUtil(context,URL,token);
        return mRetrofitUtil.getServer(server);
    }



    public static ShopMallApi getApi(Context context) {
        return HttpApiUtils.getApi(context,ShopMallApi.class);
    }










}
