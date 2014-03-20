package com.frm.bdTask.controls;

import android.content.Intent;
import android.os.Message;
import android.util.Log;
import com.frm.bdTask.BirdyApp;
import com.frm.bdTask.activities.ActivityBase;
import com.frm.bdTask.models.DataModel;
import com.frm.bdTask.services.BirdyService;
import com.frm.bdTask.utils.CommonUtil;

/**
 * @author Larry Pham.
 * @since 3/19/2014.
 */
public abstract class ActivityControlBase {
    public static final String TAG = Properties.PREFIX + ActivityControlBase.class.getSimpleName();

    protected ActivityBase mActivityBase;
    protected BirdyApp mApplication;
    protected DataModel mDataModel;
    private ActionCountHelper mCounter = new ActionCountHelper();

    public ActivityControlBase(ActivityBase activity, DataModel model){
        this.mActivityBase = activity;
        this.mDataModel = model;
        this.mApplication = (BirdyApp) activity.getApplicationContext();

    }

    public ActivityControlBase(ActivityBase activity, DataModel model, boolean blnFirst){
        this.mActivityBase = activity;
        this.mDataModel = model;
        this.mApplication = (BirdyApp) activity.getApplicationContext();
        if(blnFirst == true){
            // Remove All handlers
        }
    }

    protected Intent obtainIntent(Message msg){
        Intent intent = new Intent(mActivityBase, BirdyService.class);
        intent.putExtra(Action.ACTION_REQUEST_OWNER, this.hashCode());
        intent.putExtra(Action.ACTION_REQUEST_MESSAGE, msg);

        return intent;
    }

    protected boolean checkOwner(Message msg){
        if(msg.arg1 == this.hashCode()){
            return true;
        }else{
            Log.d(TAG, String.format("[PROCESS] checkOwner false(%d:%d)", msg.arg1, this.hashCode()));
            return false;
        }
    }

    public void setActionCountHelper(ActionCountHelper helper){
        this.mCounter = helper;
    }

    public ActionCountHelper getCounter(){
        return this.mCounter;
    }
}
