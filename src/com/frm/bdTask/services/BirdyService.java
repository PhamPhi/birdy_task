package com.frm.bdTask.services;

import com.frm.bdTask.BirdyApp;
import com.frm.bdTask.controls.Action;
import com.frm.bdTask.controls.Properties;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public class BirdyService extends Service {
	public static final String TAG = Properties.PREFIX
			+ BirdyService.class.getSimpleName();

	public BirdyApp mApp;
	public Handler mainHandler;
	private final IBinder mBinder = new LocalBinder();

	@Override
	public void onCreate() {
		mApp = (BirdyApp) getApplicationContext();
		mainHandler = mApp.getAppMainHandler();
		mApp.setBirdyService(this);

		registerReceiver(mReceiver, new IntentFilter(
				Action.ACTION_CONFIGURATION_CHANGED));
		registerReceiver(mReceiver, new IntentFilter(
				Action.ACTION_FINISH_APPLICATION));
	}

	@Override
	public void onDestroy() {
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}

	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			String fn = "onReceive...";
			Log.d(TAG, String.format(
					"Receiving the intent: %s with action[%s]",
					intent.hashCode(), intent.getAction()));
			if (action.equals(Action.ACTION_CONFIGURATION_CHANGED)) {

			}

		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, String.format("ACTIVITY_SERVICE onBind..."));
		return mBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	/**
	 * Class LocalBinder. Defining the local binder
	 * 
	 * @author Larry Pham
	 * 
	 */
	public class LocalBinder extends Binder {
		public BirdyService getService() {
			return BirdyService.this;
		}
	}
}
