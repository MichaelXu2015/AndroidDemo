package com.android.demo.http.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    private Context mContext;
    public static SpUtil spUtil = null;


    public static final String USER_INFO = "user_info";
    public static final String TOKEN = "token";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String FULL_NAME  = "full_name";


    private SpUtil(Context mContext){
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences("shop",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SpUtil getSpUtil(Context context){
        if(spUtil == null){
            spUtil = new SpUtil(context);
        }
        return spUtil;
    }


    public void saveString(String name,String value){
        editor.putString(name,value);
        editor.commit();
    }


    public String getString(String name){
      return  sharedPreferences.getString(name,"");
    }


}
