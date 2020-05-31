package com.it.uncle.androidassert;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.it.uncle.lib.util.AndroidAssert;

public class TestActivity extends MainActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        AndroidAssert.assertNotNull(getIntent());
    }
}
