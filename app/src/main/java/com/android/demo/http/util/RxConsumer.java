package com.android.demo.http.util;


import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Administrator on 2018/2/2/002.
 */

public class RxConsumer implements Observer {

    private IHttpCallback mCallback;

    private String TAG = "RxConsumer";

    private String url;
    private String value;

    public RxConsumer(IHttpCallback iHttpCallback){
        this.mCallback = iHttpCallback;
    }

    public RxConsumer(String url, String value, IHttpCallback callback) {
        mCallback = callback;
        this.url = url;
        this.value = value;
    }

    @Override
    public void onSubscribe(Disposable d) {
        //LogUtils.e("onSubscribe");
    }

    @Override
    public void onNext(Object value) {
        //LogUtils.e("onNext =" + value.toString());
        if (mCallback != null) {
            mCallback.onSuccess(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        String msg = "error";
        if (e instanceof SocketTimeoutException) {
            msg = "服务器响应超时";
            Log.d(TAG,msg);
        } else if (e instanceof ConnectException) {
            msg = "服务器连接超时";
            Log.d(TAG,msg);
        } else if (e instanceof HttpException) {
            msg = "服务器响应超时";
            Log.d(TAG,msg);
        } else {
            msg = "未知异常";
            Log.d(TAG,msg);
        }
        if (mCallback != null) {
            mCallback.onFailed(msg);
        }
    }

    @Override
    public void onComplete() {
        //LogUtils.e("onComplete");
        if (mCallback != null) {
            mCallback.onFinished();
        }
    }

    public interface IHttpCallback {
        void onSuccess(Object value);

        void onFailed(String msg);

        void onFinished();
    }
}
