package com.frm.bdTask.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import com.frm.bdTask.controls.Properties;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author: Larry Pham.
 * @since: 3/20/2014.
 * @version: 2014.03.20.
 *
 * BitmapUtil have been used to reuse the helper methods for other components can be eager to get the functions
 * of this class.
 */
public class BitmapUtil {

    private static final String TAG = Properties.PREFIX + BitmapUtil.class.getSimpleName();

    public static class ImagePool {
        private int MAX_ITEM_COUNT = 30;
        private int RECYCLE_ITEM_COUNT = 10;
        private static HashMap<String, ImagePool> imagePoolHashMap = new HashMap<String, ImagePool>();
        private List<BitmapItem> mBitmapItems = new ArrayList<BitmapItem>();

        public static ImagePool resolve(String key) {
            if (imagePoolHashMap.containsKey(key)) {
                return imagePoolHashMap.get(key);
            } else {
                ImagePool pool = new ImagePool();
                imagePoolHashMap.put(key, pool);
                return pool;
            }
        }

        public ImagePool() {
        }

        public ImagePool(int maxItemCount, int recycleItemCount) {
            this.MAX_ITEM_COUNT = maxItemCount;
            this.RECYCLE_ITEM_COUNT = recycleItemCount;
        }

        /**
         * Method getBitmap(). It've used to pull the Bitmap Item from this Pool Storage by checking the key, tagged
         * string indicated to this BitmapItem.
         * @param key The Tagged Key, Indicated to the BitmapItem.
         * @return Bitmap Type.
         */
        public Bitmap getBitmap(String key) {
            BitmapItem item = null;
            for (Iterator<BitmapItem> iter = mBitmapItems.iterator(); iter.hasNext();) {
                item = iter.next();
                if (item.key.equals(key)) {
                    if (mBitmapItems.remove(key)) {
                        mBitmapItems.add(item);
                        Log.d(TAG, String.format("Bitmap(ID:%s) move first position, total(%d)", key, mBitmapItems.size()));

                    }
                    return item.bitmap;
                }
            }
            return null;
        }

        /**
         * Method putBitmap(). It've used to push the BitmapItem to the Pool Storage by checking the key, tagged indicator
         * of the BitmapItem.
         * @param key The Indicator of BitmapItem.
         * @param bitmap Bitmap Type.
         */
        public void putBitmap(String key, Bitmap bitmap) {
            BitmapItem item = null;
            for (Iterator<BitmapItem> it = mBitmapItems.iterator(); it.hasNext(); ) {
                item = it.next();
                if (item.key.equals(key)) {
                    return;
                }
                item = new BitmapItem(key, bitmap);
                if (item.key.equals(key)){
                    return;
                }
            }
            item = new BitmapItem(key, bitmap);
            if(mBitmapItems.size() >= MAX_ITEM_COUNT){
                for(int i=0; i<RECYCLE_ITEM_COUNT; i++){
                    BitmapItem oldItem = mBitmapItems.get(i);
                    oldItem.bitmap.recycle();
                    Log.i(TAG, String.format("Bitmap(ID: %s) recycled", oldItem.key));
                    mBitmapItems.remove(0);
                }
            }
            mBitmapItems.add(item);
        }

        /**
         * Method clear(). It've used fo clear all of objects into the Pool of Images.
         * removing all of items of the Pool.
         */
        public void clear(){
            for (Iterator<BitmapItem> iter = mBitmapItems.iterator(); iter.hasNext();){
                iter.next().bitmap.recycle();
            }
            mBitmapItems.clear();
            Log.i(TAG, String.format("All Bitmap recycled"));
        }

        /**
         * Method getBitmapSize(). It's used to get the Bitmap's size by 2 values: width and height.
        * @param fileName The Input File.
         * @return Size Type.
         */
        public static Size getBitmapSize(String fileName){
            try{
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(fileName, options);
                return new Size(options.outWidth, options.outHeight);
            }catch (Exception e){
                return null;
            }
        }

        /**
         * Method getBitmapSize(). It's used to get the Bitmap's size by the array of bytes data.
         * @param bitmap Bitmap Type. The raw file( Array of Byte Type)
         * @return The Size Type. This type included 2 non scalable values: width and height.
         */
        public static Size getBitmapSize(byte[] bitmap){
            try{
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length, options);
                return new Size(options.outWidth, options.outHeight);
            }catch (Exception e){
                return null;
            }
        }

        /**
         * The class BitmapItem which have used for storing the Bitmap with tagged text into the Pool of Images.
         * -- Field: Key, the tagged text which will be clued into the Pool of Images.
         * -- Field: Bitmap, the real-content of BitmapItem Object...
         */
        public class BitmapItem {
            public String key;
            public Bitmap bitmap;

            public BitmapItem(String key, Bitmap bitmap) {
                this.key = key;
                this.bitmap = bitmap;
            }
        }
    }
    public static int calculateSampleSize(byte buffer[], Size optSize){
        try{
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(buffer, 0, buffer.length, options);
            if(options.outWidth / 16 > optSize.width){
                return 16;
            }else if(options.outWidth / 8 > optSize.width){
                return 8;
            }else if(options.outWidth / 4 > optSize.width){
                return 4;
            }else if(options.outWidth / 2 > optSize.width){
                return 2;
            }else{
                return 1;
            }

        }catch (Exception e){
            return 1;
        }
    }
    public static void setBackgroundImage(Context context, View view, int resId){
        InputStream inStream = context.getResources().openRawResource(resId);
        BitmapDrawable  bitmapDrawable = new BitmapDrawable();
        view.setBackgroundDrawable(bitmapDrawable);
    }



}
