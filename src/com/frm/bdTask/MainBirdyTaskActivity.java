package com.frm.bdTask;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import com.frm.bdTask.activities.ActivityBase;
import com.frm.bdTask.activities.cusviews.drawer.NavigationDrawerAdapter;
import com.frm.bdTask.activities.cusviews.drawer.NavigationDrawerItem;

import java.util.ArrayList;

public class MainBirdyTaskActivity extends ActivityBase {

    protected DrawerLayout DrawerLayout;
    protected ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;

    // Declaring the variables for testing the NavigationDrawer operation
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mNavMenuTitles;
    private TypedArray mNavMenuIcons;

    private ArrayList<NavigationDrawerItem> mNavDrawerItems;
    private NavigationDrawerAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        mTitle = mDrawerTitle = getTitle();
    }

    @Override
    public void invalidate() {

    }

    @Override
    public void invalidate(Object param) {

    }


}
