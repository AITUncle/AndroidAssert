package com.it.uncle.lib.util;

import android.os.Looper;

public class ThreadTypeUtil {

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static boolean isSubThread() {
        return !isMainThread();
    }

}
