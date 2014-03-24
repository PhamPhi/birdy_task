package com.frm.bdTask;

import android.view.View;

/**
 * @author: Larry Pham.
 * @since: 3/24/2014.
 * @version: 2014.03.24.
 *
 * The abstract class MenuDrawerItem used for defining the Iconic Drawer Item into the List of Items of the DrawerLayout.
 * We also configure other method to determine the last section of items during while using this DrawerLayout.
 */
public abstract class MenuDrawerItem {

    public static int INVALID_ITEM_ID = -1;

    abstract public void onSelecteItem();

    public boolean isVisible() {
        return true;
    };

    public Boolean isSelected() {
        return false;
    }

    public void onConfigureView(View view) {
    };

    protected int mTitle; // Resources Id for title string
    protected int mIconRes; // Resources Icon for title Icon Image
    protected int mItemId; // Resources Item Id for DrawerItem

    protected MenuDrawerItem(int inItemId, int inStringRes, int inIconRes) {
        this.mTitle = inStringRes;
        this.mIconRes = inIconRes;
        this.mItemId = inItemId;
    }

    protected MenuDrawerItem(int inStringRes, int inIconRes){
        this(INVALID_ITEM_ID, inStringRes, inIconRes);
    }

    /***
     * Method hasItemId() determines if the item has an id for remembering the last selected item
     * @return Boolean Type.
     */
    public boolean hasItemId(){
        return getItemId() != INVALID_ITEM_ID;
    }

    /**
     * Get the item's unique id
     * @return Integer Type.
     */
    public int getItemId() {
        return mItemId;
    }

    /**
     * Return the item's tring representation ( used by the ArrayAdapter.getView)
     * @return String Type.
     */
    public String toString(){
        return "";
    }

    /**
     * Return the resources id for the menu item's title.
     * @return Integer Type.
     */
    public int getTitleRes(){
        return mTitle;
    }

    /**
     * Return the resources id for the menu item's Icon.
     * @return Integer Type.
     */
    public int getIconRes(){
        return mIconRes;
    }

    public void selectItem(){
        onSelecteItem();
    }

    public void configureView(View view){
        onConfigureView(view);
    }
}
