package com.frm.bdTask;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.internal.widget.IcsSpinner;
import com.frm.bdTask.activities.ActivityBase;
import com.frm.bdTask.activities.cusviews.drawer.NavigationDrawerAdapter;
import com.frm.bdTask.activities.cusviews.drawer.NavigationDrawerItem;
import com.frm.bdTask.controls.Action;
import com.frm.bdTask.controls.ControlMessage;
import com.frm.bdTask.services.BirdyService;
import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;

public class MainBirdyTaskActivity extends ActivityBase {

    protected MenuDrawer mMenuDrawer;

    protected ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;

    // Declaring the variables for testing the NavigationDrawer operation

    private ArrayList<NavigationDrawerItem> mNavDrawerItems= new ArrayList<NavigationDrawerItem>();
    private NavigationDrawerAdapter mAdapter;

    public Handler mMainHandler;

    protected boolean mFirstLaunch = false;
    protected IcsSpinner mNoteSpinner;

    protected boolean isShouldAnimatedRefreshButton;
    protected boolean mShouldFinish;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

    }

    class MainHandler extends Handler {
        public static final int NOTIFY_NEW_VERSION = 1;
        public static final int REQUEST_NOTICE = 3;

        public void handleMessage(Message message) {
            switch (message.what) {
                case NOTIFY_NEW_VERSION:
                    onCheckNewVersion();
                    break;
                case REQUEST_NOTICE:
                    sendRequestNotice();
                    break;
            }
        }
    }

    public void onCheckNewVersion() {
        if (VersionManager.isExistedBirdyApp(this.getApplicationContext())) return;
        Thread checkVersion = new Thread() {
            public void run() {
                mMainHandler.sendMessage(mMainHandler.obtainMessage(MainHandler.REQUEST_NOTICE));
            }
        };
        checkVersion.setName("BirdyChecker");
        checkVersion.start();
    }

    public void sendRequestNotice() {
        // Sometime, we need adding more extra for checking languages, mcc or GPS at here,.
        Intent intent = new Intent(this, BirdyService.class);
        intent.setAction(Action.ACTION_REQUEST_NOTICE_LIST);
        intent.putExtra(Action.ACTION_REQUEST_OWNER, ((Object) this).hashCode());
        intent.putExtra(Action.ACTION_REQUEST_MESSAGE, ControlMessage.REQUEST_TO_SERVER_NOTICE_LIST);
        startService(intent);
    }

    @Override
    public void invalidate() {

    }

    @Override
    public void invalidate(Object param) {

    }

    /**
     * MenuAdapter Class used for configuring the view of MenuDrawers.
     */
    public static class MenuAdapter extends ArrayAdapter<MenuDrawerItem>{

        public MenuAdapter(Context context){
            super(context, R.layout.drawer_navigation_list_item, R.id.slider_item_title, new ArrayList<MenuDrawerItem>());
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View view = super.getView(position, convertView, parent);
            MenuDrawerItem item = getItem(position);

            if (view != null) {
                TextView titleTextView = (TextView) view.findViewById(R.id.slider_item_title);
                titleTextView.setText(item.getTitleRes());
                ImageView imageDrawerItem = (ImageView) view.findViewById(R.id.slider_item_icon);
                imageDrawerItem.setImageResource(item.getIconRes());
                // Hiding the badger

                view.findViewById(R.id.slider_item_badger).setVisibility(View.GONE);
                if(item.isSelected()){
                    int bottom = view.getPaddingBottom();
                    int top= view.getPaddingTop();
                    int right = view.getPaddingRight();
                    int left = view.getPaddingLeft();

                    view.setPadding(left, top, right, bottom);
                    view.setBackgroundResource(android.R.color.holo_blue_dark);
                }else{
                    view.setBackgroundResource(R.drawable.slider_menu_selector);
                }
            }
            item.configureView(view);
            return view;
        }
    }

    @Override
    public void onBackPressed() {
        if(mMenuDrawer != null){
            final int drawerState= mMenuDrawer.getDrawerState();
            if(drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING){
                mMenuDrawer.closeMenu();
                return;
            }
        }
        super.onBackPressed();
    }

}
