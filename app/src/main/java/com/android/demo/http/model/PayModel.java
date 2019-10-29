package com.android.demo.http.model;

import android.content.Context;
import android.util.Log;

import com.android.demo.http.api.ShopMallApi;
import com.android.demo.http.bean.ProductCategoryBean;
import com.android.demo.http.util.HttpApiUtils;
import com.android.demo.http.util.IHttpRespMessage;
import com.android.demo.http.util.RxConsumer;
import com.blankj.utilcode.util.NetworkUtils;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PayModel {

    private ShopMallApi shopMallApi;
    private final String TAG = "PayModel";
    private Context mContext;


    public PayModel(Context mContext){
        this.mContext = mContext;
        shopMallApi = HttpApiUtils.getApi(mContext);

    }


    /**
     * 根据用户名和支付密码查询用户账号信息
     */
    public void findUAByUnamePsd(String userName,String payPass,
                                 Float payMoney,String orderNo,final IHttpRespMessage<ProductCategoryBean> iHttpRespMessage){
        if(!NetworkUtils.isConnected()){
            iHttpRespMessage.networkConnectError("网络未连接,请检查网络");
        }
        shopMallApi.findUAByUnamePsd(userName,payPass,payMoney,orderNo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer(new RxConsumer.IHttpCallback() {
                    @Override
                    public void onSuccess(Object value) {
                        String successMsg = value.toString();
                        Log.d(TAG,"onSuccess : "+successMsg);




                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d(TAG,"onFailed : "+msg);
                        iHttpRespMessage.errorMsg("服务器错误");
                    }

                    @Override
                    public void onFinished() {

                    }
                }));
    }
}
