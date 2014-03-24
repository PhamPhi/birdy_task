package com.frm.bdTask.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.frm.bdTask.controls.Properties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public class CommonUtil {
    public static final String TAG = Properties.PREFIX + "[CommonUtil]";
    public static Size screenSize = null;

    public static String getDateTime(Date date, String strFormat){
        Date currentDate = date;
        if (strFormat == null){
            strFormat = "yyyyMMddHHmmss";
        }
        DateFormat dateFormat = new SimpleDateFormat(strFormat);
        if(currentDate == null){
            currentDate = new Date();
        }
        return dateFormat.format(currentDate);
    }
    /**
     * Method getTextWith() used to get the TextView's Content
     * @param textView The processed TextView.
     * @param text The Content of TextView
     * @return
     */
    public static int getTextWidth(TextView textView, String text) {
        Rect bounds = new Rect();
        Paint textPaint = textView.getPaint();
        textPaint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }


    public static int getButtonTextWidth(Button button, String text) {
        Rect bounds = new Rect();
        Paint buttonPaint = button.getPaint();
        buttonPaint.getTextBounds(text, 0, text.length(), bounds);
        return bounds.width();
    }

    /**
     * Method getDensity() used to get the Device's Density. With this function, we can use for checking the device's
     * density and supporting for other devices.
     * @param context The Application Context.
     * @return Integer Type.
     */
    public static int getDensity(Context context){
        final WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        final DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }

    /**
     * Method getScreenSize() used to get the Device's Screen Size. Using this function, we can use this function for
     * getting the ScreenSize for supporting the multiple screen compability.
     * @param context The Application Context.
     * @return Size Type.
     */
    public static Size getScreenSize(Context context) {
        if (screenSize == null) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            if (width > height) {
                screenSize = new Size(height, width);
            } else {
                screenSize = new Size(width, height);
            }
        }
        return screenSize;
    }

    /***
     * Method getDisplaySize() used to get the Display's Size. Using this function or method for checking the display's
     * size. It will return the amount of real display's size followed by 2 direction: horizontal and vertical.
     * @param context The Application Context.
     * @return Size Type.
     */
    public static Size getDisplaySize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        return new Size(width, height);
    }

    /**
     * Method getDeviceScreenWidth(), It's used for checking the screen's with and To develop the functionality of horizontal
     * direction or vertical direction, we can used this for seperating the raised issues
     * @return Integer Type.
     */
    public static int getDeviceScreenWidth(Context context) {
        int width;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            width = wm.getDefaultDisplay().getHeight();
        }else{
            width = wm.getDefaultDisplay().getWidth();
        }
        return width;
    }

    /**
     * Method getDeviceScreenHeight(). It's used for checking the screen's with and To develop the functionality of horizontal
     * direction or vertical direction, we can used this for seperating the raised issues
     * @return Integer Type.
     */
    public static int getDeviceScreenHeight(Context context) {
        int height;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            height = wm.getDefaultDisplay().getWidth();
        }else{
            height = wm.getDefaultDisplay().getHeight();
        }
        return height;

    }

    public static  float convertPixelsToDp(Context context, float px){
        DisplayMetrics metrics=  context.getResources().getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }

    public static int convertDpToPixelInt(Context context, float dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = (int)(dp * (metrics.densityDpi / 160f));
        return px;
    }

    public static float convertDpToPixel(Context context, float dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = (float) ( dp * (metrics.densityDpi / 160f));
        return  px;
    }

    /**
     * Determining the Screen's Size is xLarge and Landscape or not..
     * @param context The Application Context.
     * @return Boolean Type.
     */
    public static boolean isXLargeAndLandscape(Context context){
        return isXLarge(context) && (context.getResources().getConfiguration().orientation
                                                            == Configuration.ORIENTATION_LANDSCAPE);
    }

    /**
     * Determining the current screen's size is xlarge or not..
     * @param context The Application Context.
     * @return Boolean Type.
     */
    public static boolean isXLarge(Context context){
        return ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) ==
            Configuration.SCREENLAYOUT_SIZE_XLARGE);
    }

    /**
     * Determining the current screen's size related to Large, XLarge or not.
     * @param context The Application Context.
     * @return boolean Type
     */
    public static boolean isLargeOrXLarge(Context context){
       int mask = (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);
       return (mask == Configuration.SCREENLAYOUT_SIZE_XLARGE) ||( mask == Configuration.SCREENLAYOUT_SIZE_LARGE);
    }

}
