package com.android.demo.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.demo.R;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {


    private LinearLayout parentLinearLayout;

    private TextView tvBack;
    private TextView tvTitle;
    private TextView tvOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initContentView(R.layout.activity_base);
        initTitleView();
    }


    public  void initTitleView(){
        tvBack = (TextView)findViewById(R.id.tvBack);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvOption = (TextView)findViewById(R.id.tvOption);

        tvTitle.setText(title());
        if(showBack()){

        }else {
            tvBack.setVisibility(View.GONE);
        }
        if(showOperation()){

        }else{
            tvOption.setVisibility(View.GONE);
        }
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tvOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperationClick();
            }
        });

    }


    public void onOperationClick(){

    }



    public boolean showOperation(){
        return false;
    }

    public boolean showBack(){
        return false;
    }

    public abstract String title();




    private void initContentView(int layoutResID) {
        // TODO Auto-generated method stub
        ViewGroup group = (ViewGroup) findViewById(android.R.id.content);  //得到窗口的根布局
        group.removeAllViews(); //首先先移除在根布局上的组件
        //创建自定义父布局
        parentLinearLayout  = new LinearLayout(this);
        parentLinearLayout.setOrientation(LinearLayout.VERTICAL);
        group.addView(parentLinearLayout); //将自定义的父布局，加载到窗口的根布局上
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);//这句话的意思就是将自定义的子布局加到parentLinearLayout上，true的意思表示添加上去

    }

    /**
     * 这句的意思表示将MainActivity的布局又加到parentLinearLayout上
     */
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, parentLinearLayout, true);
        //绑定需要放在子布局加载之后
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        parentLinearLayout.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        parentLinearLayout.addView(view,params);
    }



    @Override
    protected void onStart() {
        super.onStart();
        if(!NetworkUtils.isConnected()){
            ToastUtils.showLong("网络未连接,请检查网络");
        }
    }



    public abstract void init();
}
