package com.android.demo.http.model;

import android.content.Context;
import android.util.Log;

import com.android.demo.http.api.ShopMallApi;
import com.android.demo.http.bean.CartBean;
import com.android.demo.http.bean.ProductCategoryBean;
import com.android.demo.http.util.HttpApiUtils;
import com.android.demo.http.util.IHttpRespMessage;
import com.android.demo.http.util.RxConsumer;
import com.android.demo.http.vo.CartDetailVO;
import com.android.demo.http.vo.ProductVO;
import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProductModel {

    private ShopMallApi shopMallApi;
    private final String TAG = "UserModel";
    private Context mContext;


    public ProductModel(Context mContext){
        this.mContext = mContext;
        shopMallApi = HttpApiUtils.getApi(mContext);

    }


    /**
     * 查询查询分类信息
     */
    public void getProductCategoryInfo( final IHttpRespMessage<ProductCategoryBean> iHttpRespMessage){
        if(!NetworkUtils.isConnected()){
            iHttpRespMessage.networkConnectError("网络未连接,请检查网络");
        }
        shopMallApi.getProductCategoryInfo()
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
                                ProductCategoryBean productCategoryBean = new Gson().fromJson(userInfo,ProductCategoryBean.class);

                                iHttpRespMessage.success(productCategoryBean);

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
                        iHttpRespMessage.errorMsg("服务器错误");
                    }

                    @Override
                    public void onFinished() {

                    }
                }));
    }


    /**
     * 查询查询分类信息
     */
    public void getProductByCategoryCode( String categoryCode,final IHttpRespMessage<List<ProductVO>> iHttpRespMessage){
        if(!NetworkUtils.isConnected()){
            //iHttpRespMessage.networkConnectError("网络未连接,请检查网络");
        }
        shopMallApi.getProductByCategoryCode(categoryCode)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer(new RxConsumer.IHttpCallback() {
                    @Override
                    public void onSuccess(Object value) {
                        String successMsg = value.toString();
                        Log.d(TAG,"onSuccess : "+successMsg);
                        try {
                            JSONObject jsonObject = new JSONObject(successMsg);
                            Integer status = jsonObject.getInt("status");
                            if(status==200){
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                List<ProductVO> list = new ArrayList<>();
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    ProductVO productVO = new ProductVO();
                                    productVO.setProductId(jsonObject1.getString("productId"));
                                    productVO.setProductName(jsonObject1.getString("productName"));
                                    productVO.setCategoryCode(jsonObject1.getString("categoryCode"));
                                    productVO.setPrice((float) jsonObject1.getDouble("price"));
                                    productVO.setDescript(jsonObject1.getString("descript"));
                                    productVO.setStock(jsonObject1.getInt("stock"));
                                    list.add(productVO);
                                    iHttpRespMessage.success(list);
                                }
                            }else{
                                String errorMsg = jsonObject.getString("msg");
                                iHttpRespMessage.errorMsg(errorMsg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d(TAG,"onFailed : "+msg);
                        iHttpRespMessage.errorMsg(msg);
                        //iHttpRespMessage.errorMsg("服务器错误");
                    }

                    @Override
                    public void onFinished() {

                    }
                }));
    }


    /**
     * 添加商品到购物车
     */
    public void insertCart(CartBean cartBean, final IHttpRespMessage<String> iHttpRespMessage){
        if(!NetworkUtils.isConnected()){
            iHttpRespMessage.networkConnectError("网络未连接,请检查网络");
        }
        shopMallApi.insertCart(cartBean)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer(new RxConsumer.IHttpCallback() {
                    @Override
                    public void onSuccess(Object value) {
                        String successMsg = value.toString();
                        Log.d(TAG,"onSuccess : "+successMsg);
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(successMsg);
                            Integer status = jsonObject.getInt("status");
                            if(status==200){
                                iHttpRespMessage.success("添加购物车成功");
                            }else {
                                iHttpRespMessage.errorMsg("添加购物车失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d(TAG,"onFailed : "+msg);
                        iHttpRespMessage.errorMsg(msg);
                        //iHttpRespMessage.errorMsg("服务器错误");
                    }

                    @Override
                    public void onFinished() {

                    }
                }));
    }



    /**
     * 查询购物车详情
     */
    public void findCartDetailCartId(String cartId, final IHttpRespMessage<List<CartDetailVO>> iHttpRespMessage){
        if(!NetworkUtils.isConnected()){
            iHttpRespMessage.networkConnectError("网络未连接,请检查网络");
        }
        shopMallApi.findCartDetailByCartId(cartId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxConsumer(new RxConsumer.IHttpCallback() {
                    @Override
                    public void onSuccess(Object value) {
                        String successMsg = value.toString();
                        Log.d(TAG,"onSuccess : "+successMsg);
                        try {
                            JSONObject jsonObject = new JSONObject(successMsg);
                            Integer status = jsonObject.getInt("status");
                            Gson gson = new Gson();
                            if(status==200){
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                List<CartDetailVO> cartDetailVOS = new ArrayList<>();
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    CartDetailVO cartDetailVO =  gson.fromJson(jsonObject1.toString(),CartDetailVO.class);
                                    cartDetailVOS.add(cartDetailVO);
                                }
                                iHttpRespMessage.success(cartDetailVOS);

                            }else {
                                iHttpRespMessage.errorMsg("获取购物车详情失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailed(String msg) {
                        Log.d(TAG,"onFailed : "+msg);
                        iHttpRespMessage.errorMsg(msg);
                        //iHttpRespMessage.errorMsg("服务器错误");
                    }

                    @Override
                    public void onFinished() {

                    }
                }));
    }


}
