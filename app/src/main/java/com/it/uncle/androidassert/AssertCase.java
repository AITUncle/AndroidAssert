package com.it.uncle.androidassert;

import android.content.Context;
import android.util.Log;

import com.it.uncle.lib.util.AndroidAssert;
import com.it.uncle.lib.util.ThreadTypeUtil;

public class AssertCase {
    private static final String TG = "vz-AssertCase";

    /**
     * 更新UI操作必须在主线程中执行，否则将会引发crash或者界面异常。
     * <p>
     * 在release版本，如果在子线程中调用updateUI，我们直接return，不做ui更新操作。
     * 但是在debug模式，如果在子线程中调用updateUI，直接出异常，让开发者发现异常调用并解决。
     */
    public void updateUI() {
        boolean isMainThread = ThreadTypeUtil.isMainThread();
        if (!isMainThread) {
            AndroidAssert.fail("updateUI must be called at main thread");
            return;
        }
        //update ui ....
        Log.i(TG, "updateUI...");
    }


    /**
     * 我们希望只在子线程中writeFile()，如果在主线程中调用会导致ui卡顿或者anr。
     * <p>
     * 如果在主线程调用writeFile();
     * 在debug模式，下将直接抛出异常 AssertionFailedError。让开发者
     * 在release模式，不会抛出异常，会正常执行writeFile()函数。
     */
    public void writeFile() {
        AndroidAssert.assertSubThread();
        // write file ...
        Log.i(TG, "writeFile...");
    }

    /**
     * 断言context为非空，如果为null，debug模式下抛出异常 AssertionFailedError
     */
    public void startMainActivity(Context context) {
        AndroidAssert.assertNotNull("context must not null", context);
        if (context == null) {
            return;
        }
        //startMainActivity...
        Log.i(TG, "startMainActivity...");
    }


}
