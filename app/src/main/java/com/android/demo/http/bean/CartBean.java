package com.android.demo.http.bean;

import java.util.List;

public class CartBean {

    private String userId;
    private List<CartDetailBean> list;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartDetailBean> getList() {
        return list;
    }

    public void setList(List<CartDetailBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "CartBean{" +
                "userId='" + userId + '\'' +
                ", list=" + list +
                '}';
    }
}
