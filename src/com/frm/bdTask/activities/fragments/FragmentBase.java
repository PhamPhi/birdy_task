package com.frm.bdTask.activities.fragments;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockFragment;
import com.frm.bdTask.controls.Properties;

/**
 * @author: Larry Pham.
 * @since: 3/25/2014.
 * @version: 2014.03.25.
 */
public abstract class FragmentBase extends SherlockFragment {
	public static final String TAG = Properties.PREFIX
			+ FragmentBase.class.getSimpleName();

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	// Invalidating the data when the View will be notified by the Service
	// Components.
	public abstract void invalidate();

	public abstract void invalidate(Object param);
}
