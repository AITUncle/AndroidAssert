package com.it.uncle.androidassert;

import android.app.Application;

import com.it.uncle.lib.util.AndroidAssert;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化----断言失败时，是否抛出异常，默认不抛出异常
//        AndroidAssert.enableThrowError(BuildConfig.DEBUG);//我们设置为debug模式下，断言失败才抛出异常
        AndroidAssert.enableThrowError(true);
    }
}
