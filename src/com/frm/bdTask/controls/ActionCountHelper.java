package com.frm.bdTask.controls;

import android.util.Log;

/**
 * @author: Larry Pham.
 * @since: 3/19/2014.
 * @version: 2014.03.19.01.01
 *
 * The ActionCount Helper class used for counting the action or exactly component which will be raised to be handled.
 * During while processing the Android Components, we must to handle the existence of components and The Appearance's
 * times of them for checking the thread or Tasks for handling the optimizing the memory.
 */
public class ActionCountHelper {

    private String TAG = Properties.PREFIX +  ActionCountHelper.class.getSimpleName();
    private int actionCount = 0;

    public synchronized void increase(){
        this.actionCount ++;
        Log.i(TAG, String.format("ActionCount is increased: %s", actionCount));
    }

    public synchronized void decrease(){
        this.actionCount --;
        Log.i(TAG, String.format("ActionCount is decreased: %s", actionCount));
    }

    public synchronized int getCount(){
        return this.actionCount;
    }

    public synchronized void reset(){
        this.actionCount = 0;
    }

}
