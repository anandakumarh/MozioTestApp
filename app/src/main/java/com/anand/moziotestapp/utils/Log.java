package com.anand.moziotestapp.utils;

/**
 * Created by ananda on 07-09-2016.
 */
public class Log {
    public static void d(String tag, String message) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.d(tag, message);
        }
    }

    public static void d(String tag, String message, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.d(tag, message, tr);
        }
    }

    public static void w(String tag, String message) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.w(tag, message);
        }
    }

    public static void w(String tag, String message, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.w(tag, message, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.w(tag, tr);
        }
    }

    public static void i(String tag, String message) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.i(tag, message);
        }
    }

    public static void i(String tag, String message, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.i(tag, message, tr);
        }
    }

    public static void e(String tag, String message) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.e(tag, message);
        }
    }

    public static void e(String tag, String message, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.e(tag, message, tr);
        }
    }

    public static void v(String tag, String message) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.v(tag, message);
        }
    }

    public static void v(String tag, String message, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.v(tag, message, tr);
        }
    }

    public static void wtf(String tag, String message) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.wtf(tag, message);
        }
    }

    public static void wtf(String tag, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.wtf(tag, tr);
        }
    }

    public static void wtf(String tag, String message, Throwable tr) {
        if (Configuration.ENABLE_LOGS) {
            android.util.Log.wtf(tag, message, tr);
        }
    }
}
