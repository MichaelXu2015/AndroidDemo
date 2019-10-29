package com.android.demo.http.bean;

public class UserLoginBean {

    private String userName;
    private String password;
    private String rule;//角色 admin or general


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "UserLoginBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
