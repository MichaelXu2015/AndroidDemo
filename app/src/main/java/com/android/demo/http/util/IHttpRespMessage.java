package com.android.demo.http.util;

public interface IHttpRespMessage<T> {

    void errorMsg(String msg);
    void success(T t);
    void networkConnectError(String msg);

}
