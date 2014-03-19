package com.frm.bdTask.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public class BirdyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
