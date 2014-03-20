package com.frm.bdTask.utils;

/**
 * @author Larry Pham.
 * @since 3/19/2014.
 *
 * The Data Type class which that used to get the information of with and height units.
 *
 */
public class Size {
    int width;
    int height;

    public Size(int inWidth, int inHeight){
        this.width = inWidth;
        this.height = inHeight;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setWidth(int inWidth){
        this.width = inWidth;
    }

    public void setHeight(int inHeight){
        this.height  = inHeight;
    }
}
