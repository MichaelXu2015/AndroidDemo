package com.android.demo.http.api;


import com.android.demo.http.bean.CartBean;
import com.android.demo.http.bean.CartDetailBean;
import com.android.demo.http.bean.OrderDetailBean;
import com.android.demo.http.bean.OrderMasterBean;
import com.android.demo.http.bean.PayOrderBean;
import com.android.demo.http.bean.UserLoginBean;
import com.android.demo.http.bean.UserRegisterBean;
import com.google.gson.JsonObject;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ShopMallApi {


    /**
     * 登录
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/shop/userapi/user/login")
    Observable<JsonObject> login(@Body UserLoginBean loginBean);


    /**
     * 注册
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/shop/userapi/user/insertUser")
    Observable<JsonObject> register(@Body UserRegisterBean userRegisterBean);



    /**
     * 通过用户名和支付密码查询用户的账号信息
     * @return
     */
    @GET("/shop/userapi/userAccount/findUAByUnamePsd/{userName}/{payPass}/{payMoney}/{orderNo}")
    Observable<JsonObject> findUAByUnamePsd(@Path("userName") String userName, @Path("payPass") String payPass
            , @Path("payMoney") Float payMoney, @Path("orderNo") String orderNo);



    /**
     * 获取产品类别信息
     * @return
     */
    @GET("/shop/productapi/productCategory/findProductCategoryInfo")
    Observable<JsonObject> getProductCategoryInfo();





    /**
     * 通过categoryCode获取产品信息
     * @return
     */
    @GET("/shop/productapi/product/findProductByCategoryCode/{categoryCode}")
    Observable<JsonObject> getProductByCategoryCode(@Path("categoryCode") String categoryCode);



    /**
     * 通过 productId获取产品信息
     * @return
     */
    @GET("/shop/productapi/product/getProduct/{productId}")
    Observable<JsonObject> getProductByProductId(@Path("productId") String productId);


    /**
     * 通过用户ID查询购物车
     * @return
     */
    @GET("/shop/productapi/cart/findCartByUserId/{userId}")
    Observable<JsonObject> getCartByUserId(@Path("userId") String userId);


    /**
     * 增加购物车
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/shop/productapi/cart/insertCart")
    Observable<JsonObject> insertCart(@Body CartBean cartBean);


    /**
     *  更新购物车
     * @return
     */
    @GET("/shop/productapi/cart/updateCartTotalPriceByUserId/{userId}/{price}")
    Observable<JsonObject> updateCart(@Path("userId") String userId, @Path("price") Float price);


    /**
     *  根据cartId查询购物车详情
     * @return
     */
    @GET("/shop/productapi/cartDetail/findCartDetailByCartId/{cartId}")
    Observable<JsonObject> findCartDetailByCartId(@Path("cartId") String cartId);



    /**
     * 增加购物车详情
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/shop/productapi/cart/insertCartDetail")
    Observable<JsonObject> insertCartDetail(@Body CartDetailBean cartDetailBean);


    /**
     * 根据订单编号查询订单
     * @return
     */
    @GET("/shop/orderapi/order/findOrderMasterByOrderNo/{orderNo}")
    Observable<JsonObject> findOrderMasterByOrderNo(@Path("orderNo") String orderNo);


    /**
     * 根据用户ID查询订单
     * @return
     */
    @GET("/shop/orderapi/order/findOrderMasterByUserId/{userId}")
    Observable<JsonObject> findOrderMasterByUserId(@Path("userId") String userId);



    /**
     * 增加订单
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/shop/orderapi/order/insertOrderMaster")
    Observable<JsonObject> insertOrderMaster(@Body OrderMasterBean orderMasterBean);


    /**
     * 根据订单编号查询订单详情
     * @return
     */
    @GET("/shop/orderapi/order/findOrderDetailByOrderNo/{orderNo}")
    Observable<JsonObject> findOrderDetailByOrderNo(@Path("orderNo") String orderNo);


    /**
     * 增加订单详情
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/shop/orderapi/order/insertOrderMaster")
    Observable<JsonObject> insertOrderMaster(@Body OrderDetailBean orderDetailBean);


    /**
     * 增加订单详情
     * @return
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("/shop/payapi/pay/insertShopPay")
    Observable<JsonObject> insertOrderMaster(@Body PayOrderBean payOrderBean);

}
