package com.frm.bdTask.models;

import android.graphics.Bitmap;

/**
 * @author: Larry Pham.
 * @since: 3/20/2014.
 * @version: 2014.03.20.
 */
public class Image {
    private String url                          = null;
    private String title                        = null;
    private String link                         = null;
    private int width                           = 0;
    private int height                          = 0;
    private String description                  = null;

    private Bitmap bitmap                       = null;

    public Image(){

    }

    public Image(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(String szHeight){
        int height = 0;
        if(szHeight != "" && szHeight != null){
            height = Integer.parseInt(szHeight) + 1;
        }
        this.height = height;
    }

    public void setWidth(String szWidth){
        int width = 0;
        if(szWidth != null && szWidth != ""){
            width = Integer.parseInt(szWidth) + 1;
        }
        this.width = width;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        setHeight(bitmap.getHeight());
        setWidth(bitmap.getWidth());
    }
}
