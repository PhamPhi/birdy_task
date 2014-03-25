package com.frm.bdTask;

import android.content.Context;
import android.content.SharedPreferences;
import com.frm.bdTask.controls.Properties;

/**
 * @author: Larry Pham.
 * @since: 3/25/2014.
 * @version: 2014.03.25.001
 *
 * Setting Related Utilities.
 */
public class BirdySettings {

    private static final String TAG = Properties.PREFIX + BirdySettings.class.getSimpleName();
    private static final String BIRDY_SETTINGS= "birdy_settings";

    public static void setString(Context context, String stringName, String value){
        SharedPreferences pref = context.getSharedPreferences(BIRDY_SETTINGS, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(stringName, value);
        editor.commit();
    }

    public static void setInt(Context context, String settingName, int value){
        setString(context, settingName, convertIntToString(value));
    }

    public static void setLong(Context context, String settingName, long value){
        setString(context, settingName, convertLongToString(value));
    }

    public static void setFloat(Context context, String settingName, float value){
        setString(context, settingName, convertFloatToString(value));
    }

    public static void setBoolean(Context context, String settingName, boolean value){
        setString(context, settingName, convertBooleanToString(value));
    }

    public static String getString(Context context, String settingName, String value){
        SharedPreferences pref = context.getSharedPreferences(BIRDY_SETTINGS, 0);
        return pref.getString(settingName, value);
    }

    public static int getInt(Context context, String settingName, int value){
        String res2= getString(context, settingName, convertIntToString(value));
        return Integer.parseInt(res2);
    }

    public static float getFloat(Context context, String settingName, float value){
        String res2= getString(context, settingName, convertFloatToString(value));
        return Float.parseFloat(res2);
    }

    public static boolean getBoolean(Context context, String settingName, boolean value){
        String res2 = getString(context, settingName, convertBooleanToString(value));
        return Boolean.parseBoolean(res2);
    }

    private static String convertIntToString(int value){
        return emptyString() +  value;
    }

    private static String convertLongToString(long value){
        return emptyString() + value;
    }

    private static String convertFloatToString(float value){
        return emptyString() + value;
    }

    private static String convertBooleanToString(boolean value){
        return value ? "true" : "false";
    }
    public static String emptyString(){
        return "";
    }


}
