package com.anand.moziotestapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by ananda on 7/9/16.
 */
public class PreferenceUtils {
    protected static final String TAG = PreferenceUtils.class.getName();

    private static final String PREFERENCE_NAME = "mozio";

    public static String getStringFromSharedPreference(Context context,
                                                       String key) {
        String value = null;
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(key)) {
            value = sharedpreferences.getString(key, null);
        }
        return value;
    }


    public static void saveStringIntoSharedPreference(Context context,
                                                      String key, String value) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        prefEditor.putString(key, value);
        prefEditor.commit();
    }

    public static long getLongFromSharedPreference(Context context,
                                                   String key) {
        long value = 0;
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        if (sharedpreferences.contains(key)) {
            value = sharedpreferences.getLong(key, 0);
        }
        return value;
    }

    public static void saveLongIntoSharedPreference(Context context,
                                                    String key, long value) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        prefEditor.putLong(key, value);
        prefEditor.commit();
    }


    public static long getLastPatientNumber(Context context) {
        long patientNumber = getLongFromSharedPreference(context,
                Constants.Preference.LAST_PATIENT_NUMBER);
        return patientNumber;
    }

    public static void updatePatientNumber(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        prefEditor.putLong(Constants.Preference.LAST_PATIENT_NUMBER,
                getLastPatientNumber(context) + 1);
        prefEditor.commit();
    }


}

