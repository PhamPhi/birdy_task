package com.frm.bdTask.controls;

import android.content.Intent;
import android.os.Message;
import android.util.Log;
import com.frm.bdTask.BirdyApp;
import com.frm.bdTask.activities.ActivityBase;
import com.frm.bdTask.models.DataModel;
import com.frm.bdTask.services.BirdyService;
import com.frm.bdTask.utils.CommonUtil;

import java.lang.ref.WeakReference;

/**
 * @author Larry Pham.
 * @since 3/19/2014.
 *
 * The abstract controller class. It've used to configure the abstract controller component which will be used for handling
 * the view, models and connecting, notify to other components into current application.
 *
 * By using this abstract class for Activity controller, we can also reconfigure again for the Fragment Controller.
 *
 */
public abstract class ActivityControlBase {
    public static final String TAG = Properties.PREFIX + ActivityControlBase.class.getSimpleName();

    private WeakReference<ActivityBase> refActivity;
    protected ActivityBase mActivityBase;
    protected BirdyApp mApplication;
    protected DataModel mDataModel;
    protected ActivityHandler mActivityHandler = new ActivityHandler() {
        @Override
        public void callBack(Message msg) {
           handleMessage(msg);
        }
    };
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
            mApplication.removeAllHandler(); // removing all handlers
        }

    }

    /**
     * Method handleMessage() used to parse the message data and then driving the process to own each of branches.
     * @param msg Message
     */
    public abstract void handleMessage(Message msg);

    /**
     * Method obtainIntent() used to obtain new Intent and adding Action for this intent object
     * @param msg Message
     * @return Intent Object.
     */
    protected Intent obtainIntent(Message msg){
        Intent intent = new Intent(mActivityBase, BirdyService.class);
        intent.putExtra(Action.ACTION_REQUEST_OWNER, this.hashCode());
        intent.putExtra(Action.ACTION_REQUEST_MESSAGE, msg);

        return intent;
    }

    /**
     * Method sendMessage() used to send a message with the given content(what) to the specified object.
     * @param what The Content
     * @param obj The destination
     */
    public void sendMessage(int what, Object obj){
        Message msg = new Message();
        msg.what= what;
        msg.obj= obj;
        handleMessage(msg);
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
