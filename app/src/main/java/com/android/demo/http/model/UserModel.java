package com.android.demo.http.model;

import android.content.Context;
import android.util.Log;

import com.android.demo.http.api.ShopMallApi;
import com.android.demo.http.bean.UserLoginBean;
import com.android.demo.http.bean.UserRegisterBean;
import com.android.demo.http.sp.SpUtil;
import com.android.demo.http.util.HttpApiUtils;
import com.android.demo.http.util.IHttpRespMessage;
import com.android.demo.http.util.RxConsumer;
import com.android.demo.http.vo.UserVO;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserModel {

    private ShopMallApi shopMallApi;
    private final String TAG = "UserModel";
    private Context mContext;
    private Gson gson;
    private SpUtil spUtil;


    public UserModel(Context mContext){
        this.mContext = mContext;
        shopMallApi = HttpApiUtils.getApi(mContext);
        gson = new Gson();
        spUtil = SpUtil.getSpUtil(mContext);
    }


    /**
     * 登录
     */
    public void login(UserLoginBean loginBean, final IHttpRespMessage<UserVO> iHttpRespMessage){
        if(!NetworkUtils.isConnected()){
            iHttpRespMessage.networkConnectError("网络未连接,请检查网络");
        }
        shopMallApi.login(loginBean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer(new RxConsumer.IHttpCallback() {
                    @Override
                    public void onSuccess(Object value) {
                        String successMsg = value.toString();
                        Log.d(TAG,"onSuccess : "+successMsg);
                        try {
                            JSONObject jsonObject = new JSONObject(successMsg);
                            int status = jsonObject.getInt("status");
                            if(status==200){
                                String userInfo = jsonObject.getString("data");
                                //保存用户信息到sp
                                spUtil.saveString(SpUtil.USER_INFO, userInfo);
                                UserVO userVO = gson.fromJson(userInfo,UserVO.class);
                                spUtil.saveString(SpUtil.TOKEN,userVO.getToken());
                                spUtil.saveString(SpUtil.USER_ID,userVO.getUserId());
                                spUtil.saveString(SpUtil.USER_NAME,userVO.getUserName());
                                spUtil.saveString(SpUtil.FULL_NAME,userVO.getFullName());
                                iHttpRespMessage.success(userVO);

                            }else{
                                String errorMsg = jsonObject.getString("msg");
                                iHttpRespMessage.errorMsg(errorMsg);
                            }
                        } catch (JSONException e) {
                            iHttpRespMessage.errorMsg(e.getMessage());
                        }


                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d(TAG,"onFailed : "+msg);
                        iHttpRespMessage.errorMsg(msg);
                    }

                    @Override
                    public void onFinished() {

                    }
                }));
    }



    /**
     * 登录
     */
    public void register(UserRegisterBean userRegisterBean, final IHttpRespMessage<String> iHttpRespMessage){
        if(!NetworkUtils.isConnected()){
            iHttpRespMessage.networkConnectError("网络未连接,请检查网络");
        }
        shopMallApi.register(userRegisterBean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer(new RxConsumer.IHttpCallback() {
                    @Override
                    public void onSuccess(Object value) {
                        String successMsg = value.toString();
                        Log.d(TAG,"onSuccess : "+successMsg);
                        try {
                            JSONObject jsonObject = new JSONObject(successMsg);
                            int status = jsonObject.getInt("status");
                            if(status==200){
                                iHttpRespMessage.success("注册成功");

                            }else{
                                String errorMsg = jsonObject.getString("msg");
                                iHttpRespMessage.errorMsg(errorMsg);
                            }
                        } catch (JSONException e) {
                            iHttpRespMessage.errorMsg(e.getMessage());
                        }


                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d(TAG,"onFailed : "+msg);
                        iHttpRespMessage.errorMsg(msg);
                    }

                    @Override
                    public void onFinished() {

                    }
                }));
    }




}
