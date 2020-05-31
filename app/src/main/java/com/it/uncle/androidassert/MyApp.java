package com.it.uncle.androidassert;

import android.app.Application;

import com.it.uncle.lib.util.AndroidAssert;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidAssert.enableThrowError(BuildConfig.DEBUG);
    }
}
