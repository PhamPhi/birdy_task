package com.frm.bdTask.activities.cusviews.drawer;

/**
 * @author: Larry Pham.
 * @since: 3/24/2014.
 * @version: 2014.03.24.
 * 
 *           The class used to test the NavigationDrawer Operation.
 */
public class NavigationDrawerItem {
	private String mTitle;
	private int mResourceId;
	private String mCount = "0";
	private boolean isCounterVisible = false;

	public NavigationDrawerItem() {

	}

	public NavigationDrawerItem(String title, int inResId) {
		this.mTitle = title;
		this.mResourceId = inResId;
	}

	public NavigationDrawerItem(String inTitle, int inIcon, boolean inVisible,
			String inCount) {
		this.mTitle = inTitle;
		this.mResourceId = inIcon;
		this.isCounterVisible = isCounterVisible;
		this.mCount = inCount;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public int getmResourceId() {
		return mResourceId;
	}

	public void setmResourceId(int mResourceId) {
		this.mResourceId = mResourceId;
	}

	public String getmCount() {
		return mCount;
	}

	public void setmCount(String mCount) {
		this.mCount = mCount;
	}

	public boolean isCounterVisible() {
		return isCounterVisible;
	}

	public void setCounterVisible(boolean isCounterVisible) {
		this.isCounterVisible = isCounterVisible;
	}
}
