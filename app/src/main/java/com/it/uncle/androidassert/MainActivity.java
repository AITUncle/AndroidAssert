package com.it.uncle.androidassert;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.it.uncle.lib.util.AndroidAssert;

public class MainActivity extends Activity {
    private static final String TG = "vz-MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化----debug模式下断言失败才会抛出异常
        AndroidAssert.enableThrowError(BuildConfig.DEBUG);

        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_updateUIAtMainThread).setOnClickListener(v -> {
            Log.e(TG, "btn_assertMainThread");
            new AssertCase().updateUI();//正常执行
        });

        findViewById(R.id.btn_updateUIAtSubThread).setOnClickListener(v -> {
            Log.e(TG, "btn_assertSubThread");
            new Thread(){
                @Override
                public void run() {
                    //在debug模式下，会抛出异常AssertionFailedError
                    new AssertCase().updateUI();
                }
            }.start();

        });

        findViewById(R.id.btn_writeFileAtMainThread).setOnClickListener(v -> {
            //在debug模式下，会抛出异常AssertionFailedError
            new AssertCase().writeFile();
        });

        findViewById(R.id.btn_startMainActivity).setOnClickListener(v -> {
            new AssertCase().startMainActivity(null);
        });


    }
}
