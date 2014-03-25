package com.frm.bdTask;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.frm.bdTask.controls.Action;
import com.frm.bdTask.controls.ActivityHandler;
import com.frm.bdTask.controls.ControlMessage;
import com.frm.bdTask.controls.Properties;
import com.frm.bdTask.models.BirdyData;
import com.frm.bdTask.models.DataModel;
import com.frm.bdTask.services.BirdyService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public class BirdyApp extends Application {

	public static final String TAG = Properties.PREFIX
			+ BirdyData.class.getSimpleName();
	public List<ActivityHandler> handlerList = new ArrayList<ActivityHandler>(); // Define
																					// the
																					// list
																					// of
																					// activity
																					// handler
	protected AppMainHandler mainAppHandler;
	// Declaring the global variables for handling all components
	public BirdyData mData = new BirdyData(this); // Define the new BDHelper for
													// building the data
	public DataModel mDataModel = new DataModel();
	public BirdyService mService = null; // Defining the Service components at
											// here,,,
	public BirdySettings mSettings = null;

	// //////////////////// GETTERS AND SETTERS METHODS FOR ACCESSING THE
	// COMPONENTS OF APPLICATION /////////////////////
	public BirdyData getData() {

		return this.mData;
	}

	public BirdySettings getSettings() {

		return this.mSettings;
	}

	public BirdyService getService() {

		return this.mService;
	}

	public DataModel getDataModel() {

		return this.mDataModel;
	}

	public AppMainHandler getAppMainHandler() {

		return this.mainAppHandler;
	}

	public void setDataModel(DataModel inModel) {
		this.mDataModel = inModel;
	}

	public void setAppMainHandler(AppMainHandler handler) {
		this.mainAppHandler = handler;
	}

	public void setSettings(BirdySettings settings) {
		this.mSettings = settings;
	}

	public void setBirdyData(BirdyData data) {
		this.mData = data;
	}

	public void setBirdyService(BirdyService service) {
		this.mService = service;
	}

	// //////////////////// METHODS FOR ADDING, REMOVING THE HANDLER OF LIST OF
	// ACTIVITIES's HANDLERS////////////////////

	/**
	 * Method addHandler() used to add the new ActivityHandler to the current
	 * main list of handlers
	 * 
	 * @param inHandler
	 *            The ActivityHandler
	 */
	public void addHandler(ActivityHandler inHandler) {
		Log.d(TAG, String
				.format("The List of Handlers will be added by %s Handler"
						+ inHandler.toString()));
		handlerList.add(inHandler);
	}

	/**
	 * Method addHandlerIfNotExisted() used to add the new handler if it was not
	 * existed into the current main list of handlers.
	 * 
	 * @param inHandler
	 *            The ActivityHandler.
	 */
	public void addHandlerIfNotExisted(ActivityHandler inHandler) {
		boolean blnExisted = false;
		try {
			for (ActivityHandler handler : handlerList) {
				if (handler.hashCode() == inHandler.hashCode()) {
					// Checking the element of list of handlers, if its hashcode
					// has equaled to the given handler's hashcode
					// Assigning the brnExisted will be equaled to "true" and
					// breacking this loop
					blnExisted = true;
					break;
				}
			}
			if (!blnExisted) {
				Log.e(TAG, "addHandlerIfNotExisted");
				addHandler(inHandler);
			}
		} catch (Exception ex) {
			Log.i(TAG,
					String.format("addHandlerIfNotExisted() throws "
							+ " Exception: " + ex.getMessage()));
			ex.printStackTrace();
		}
	}

	/**
	 * Method removeAllHandler() used to remove all of handlers into the current
	 * main list of Activity's Handler. using it into the case duringwhile
	 * handling the activities or other UI components.
	 */
	public void removeAllHandler() {
		Log.d(TAG,
				String.format("Removing all handlers of the current list of ActivityHandler"));
		handlerList.clear();
	}

	/**
	 * Method removeHandler() used to remove the given handler from the current
	 * main list of handlers.
	 * 
	 * @param inHandler
	 *            The ActivityHandler.
	 */
	public void removeHandler(ActivityHandler inHandler) {
		Log.i(TAG,
				String.format("Removing the %s Handler" + inHandler.toString()));
		handlerList.remove(inHandler);
		Log.d(TAG, String.format(
				"The size of list of handlers after removing %s Handler: %d",
				inHandler.toString(), handlerList.size()));

	}

	class AppMainHandler extends Handler {
		public void handleMessage(Message msg) {
			String fn = "handleMessage...";
			Log.d(TAG, String.format("[AppMainHandler]." + fn + msg.toString()));
			// Parse the message's content and driven to call the branch of
			// handlers.
			switch (msg.what) {
			case ControlMessage.REQUEST_TO_SERVER_NOTICE_LIST:
				// TODO adding some blocks of codes at here for handling this
				// situation
				break;
			case ControlMessage.COMMIT_TO_DB_COMPLETE:
				// TODO adding some blocks of codes at here for handling this
				// situation
				break;
			case ControlMessage.REQUEST_TO_SERVER_NOTICE_LIST_COMPLETE:

				// Adding more other situations.
			}
			for (ActivityHandler handler : handlerList) {
				handler.callBack(msg); // Calling back message for handling ...
			}
		}
	}

	private final BroadcastReceiver mAppReceiver = new BroadcastReceiver() {
		// TODO adding the blocks of code for handling the action of
		// configuration changed and others..
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Log.d(TAG, String.format("OnReceive: Action[%s]", action));
			if (action != null) {
				if (action.equals(Intent.ACTION_CONFIGURATION_CHANGED)) {

				}
			}
		}
	};

	// /////////////////////////// SHOW AND HIDING ALERT DIALOG
	// /////////////////////////////////////////////////////
	public void showProgressDialog(boolean cancelable) {
		Intent intent = new Intent();
		intent.setAction(Action.ACTION_SHOW_DIALOG_PROGRESS);
		sendBroadcast(intent);
	}

	public void dismissDialog(boolean isDismiss) {
		Intent intent = new Intent();
		intent.setAction(Action.ACTION_DISMISS_DIALOG);
		sendBroadcast(intent);
	}

}
