package com.frm.bdTask.controls;

import android.os.Message;

/**
 * @author Larry Pham
 * @since 19.03.2014
 * @version 2014.03.19.01.0.1
 *
 */
public abstract class ActivityHandler {
    /**
     * Method callBack() used to handle other method by parsing message
     * @param msg Message
     */
    public abstract void callBack(Message msg);
}
