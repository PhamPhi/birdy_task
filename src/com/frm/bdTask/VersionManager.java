package com.frm.bdTask;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.frm.bdTask.controls.Properties;

/**
 * @author: Larry Pham.
 * @since: 3/24/2014.
 * @version: 2014.03.24.
 */
public class VersionManager {
    public static final String TAG = Properties.PREFIX + "VersionManager";
    public static final String APP_ID = "com.frm.bdTask";

    /**
     * Determining the current App's version name. Reusing for checking the installation of this app or not.
     * @param context The Application Context.
     * @return String type.
     */
    public static String getBirdyTaskVersion(Context context){
        Log.d(TAG, "getBirdyTaskVersion");
        String versionName = null;
        try{
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * Determinging the Current App's Version Code. Reusing for checking the ability to update or upgrade this app.
     * @param context The Application Context.
     * @return String type.
     */
    public static int getVersionCode(Context context){
        Log.d(TAG, "getBirdyTaskVersionCode");
        int versionCode = 0;
        try{
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    public static boolean isExistedBirdyApp(Context context){
        Log.d(TAG, "isExistBirdyApp");
        PackageManager pm = context.getPackageManager();
        try{
            if(pm != null) {
                pm.getPackageInfo("com.frm.bdyTask", PackageManager.GET_ACTIVITIES);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
