package com.frm.bdTask.activities;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.frm.bdTask.controls.ActivityControlBase;
import com.frm.bdTask.controls.Properties;
import com.frm.bdTask.models.Image;

/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public abstract class ActivityBase extends FragmentActivity {
    public static final String TAG = Properties.PREFIX + ActivityBase.class.getSimpleName();
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Method getTopActivity(). It's used to get the floating activity during while checking
     * @return String Type.
     */
    protected String getTopActivity(){
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName topActivity = am.getRunningTasks(1).get(0).topActivity;
        Log.d(TAG, "TopActivity: " + topActivity.getPackageName() + ", ClassName: " + topActivity.getClassName());
        return topActivity.getClassName();
    }

    public Image getDefaultImage(String key){
        return null;
    }


    protected ActivityControlBase control = null;

    public abstract void invalidate();
    public abstract void invalidate(Object param);
}
