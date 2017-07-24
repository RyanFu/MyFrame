package com.jey.mymvp.base;

import android.app.Application;

/**
 * Created by xyan on 2016/6/2.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        CrashHandler.getInstance().init(this);//初始化全局异常管理   可自行修改,用自己的异常处理
        //各种初始化
    }

}
