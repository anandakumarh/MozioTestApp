package com.anand.moziotestapp.utils;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Kashi on 15-07-2016.
 */
public class Configuration {
    public static final String CONFIGURABLE_BASE_URL = "";
    public static HttpLoggingInterceptor.Level HTTP_LOG_LEVEL = HttpLoggingInterceptor.Level.BODY;
    public static boolean ENABLE_LOGS = true;
}
