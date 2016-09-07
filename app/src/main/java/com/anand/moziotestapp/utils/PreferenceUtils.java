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

    /**
     * Method which retrieves string from the preference.
     * @param context
     * @param key
     * @return String value
     */
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


    /**
     * Method used to save string into preference
     * @param context
     * @param key
     * @param value
     */
    public static void saveStringIntoSharedPreference(Context context,
                                                      String key, String value) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        prefEditor.putString(key, value);
        prefEditor.commit();
    }

    /**
     * Method which returns long value from preference
     * @param context
     * @param key
     * @return
     */
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

    /**
     * Method used to save long value into preference
     * @param context
     * @param key
     * @param value
     */
    public static void saveLongIntoSharedPreference(Context context,
                                                    String key, long value) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        prefEditor.putLong(key, value);
        prefEditor.commit();
    }


    /**
     * Method which retrieves last patient number
     * @param context
     * @return
     */
    public static long getLastPatientNumber(Context context) {
        long patientNumber = getLongFromSharedPreference(context,
                Constants.Preference.LAST_PATIENT_NUMBER);
        return patientNumber;
    }

    /**
     * Method which updates patient number
     * @param context
     */
    public static void updatePatientNumber(Context context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences(
                PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedpreferences.edit();
        prefEditor.putLong(Constants.Preference.LAST_PATIENT_NUMBER,
                getLastPatientNumber(context) + 1);
        prefEditor.commit();
    }


}

