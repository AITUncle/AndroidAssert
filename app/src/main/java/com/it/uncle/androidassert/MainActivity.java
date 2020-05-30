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
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_assertMainThread).setOnClickListener(v -> {
            Log.e(TG, "btn_assertMainThread");
            AndroidAssert.assertMainThread();
        });

        findViewById(R.id.btn_assertSubThread).setOnClickListener(v -> {
            Log.e(TG, "btn_assertSubThread");
            AndroidAssert.assertSubThread();
        });
    }
}
