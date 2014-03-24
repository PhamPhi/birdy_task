package com.frm.bdTask.activities;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.frm.bdTask.MenuDrawerItem;
import com.frm.bdTask.R;
import com.frm.bdTask.controls.Action;
import com.frm.bdTask.controls.ActivityControlBase;
import com.frm.bdTask.controls.Properties;
import com.frm.bdTask.models.DataModel;
import com.frm.bdTask.models.Image;

/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public abstract class ActivityBase extends SherlockFragmentActivity {
    public static final String TAG = Properties.PREFIX + ActivityBase.class.getSimpleName();

    protected boolean mShouldFinished= false;
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

    protected void startActivityWithDelay(final Intent intent){
        startActivity(intent);
    }
    protected ActivityControlBase control = null;

    public abstract void invalidate();
    public abstract void invalidate(Object param);

    protected BroadcastReceiver mDrawerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent == null || intent.getAction() == null){
                return;
            }
            if(intent.getAction().equals(Action.ACTION_REQUEST_OWNER)){
                // Doing request owner.
            }
            if(intent.getAction().equals(Action.ACTION_REQUEST_DATA)){
                // Doing request data.
            }

        }
    };

    /////////////////////////////// NAVIGATION ITEMS ///////////////////////////////////////////////////////////////
    public class ListNoteMenuItem extends MenuDrawerItem {

        protected ListNoteMenuItem(int inItemId, int inStringRes, int inIconRes) {
            super(DataModel.LIST_NOTE_ACTIVITY, R.string.menu_drawer_item_list_notes, R.drawable.menu_drawer_note);
        }

        @Override
        public boolean isVisible() {
            return super.isVisible();
        }

        protected ListNoteMenuItem(int inStringRes, int inIconRes) {
            super(inStringRes, inIconRes);
        }

        @Override
        public Boolean isSelected() {
            return ActivityBase.this instanceof ListNotesActivity;
        }

        @Override
        public void onSelecteItem() {
           if(!isSelected()){
               mShouldFinished = true;

               Intent intent= new Intent(ActivityBase.this, ListNotesActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
               startActivityWithDelay(intent);
           }
        }
    }

}
