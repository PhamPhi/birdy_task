package com.frm.bdTask.activities.cusviews.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.frm.bdTask.R;

import java.util.ArrayList;

/**
 * @author: Larry Pham.
 * @since: 3/24/2014.
 * @version: 2014.03.24.
 */
public class NavigationDrawerAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<NavigationDrawerItem> navDrawerItems;

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView != null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(
					R.layout.drawer_navigation_list_item, null);
		}
		ImageView imageResource = (ImageView) convertView
				.findViewById(R.id.menu_slider_item_icon);
		TextView mTitle = (TextView) convertView
				.findViewById(R.id.slider_item_title);
		TextView mCount = (TextView) convertView
				.findViewById(R.id.slider_item_badger);

		imageResource.setImageResource(navDrawerItems.get(position)
				.getmResourceId());
		mTitle.setText(navDrawerItems.get(position).getmTitle());

		if (navDrawerItems.get(position).isCounterVisible()) {
			mCount.setText(navDrawerItems.get(position).getmCount());
		} else {
			mCount.setVisibility(View.GONE);
		}
		return convertView;
	}
}
