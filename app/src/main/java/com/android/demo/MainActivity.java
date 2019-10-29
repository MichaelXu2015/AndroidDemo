package com.android.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.demo.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public String title() {
        return "登录";
    }

    @Override
    public void init() {

    }

    @Override
    public boolean showBack() {
        return false;
    }

    @Override
    public boolean showOperation() {
        return true;
    }
}
