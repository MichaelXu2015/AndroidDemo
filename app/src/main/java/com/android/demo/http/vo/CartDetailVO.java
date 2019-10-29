package com.android.demo.http.vo;

import java.io.Serializable;

public class CartDetailVO implements Serializable{

    /**
     * cartId : b9d40f11cc474289b7c7336896bfc8ca
     * cartDetailId : d0d0b603e3
     * productId : 100100101
     * productName : 小米4手机
     * productPrice : 1999.99
     * quantity : 1
     * subtotal : 1999.99
     */

    private String cartId;
    private String cartDetailId;
    private String productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private double subtotal;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(String cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "CartDetailVO{" +
                "cartId='" + cartId + '\'' +
                ", cartDetailId='" + cartDetailId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                '}';
    }
}
