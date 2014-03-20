package com.frm.bdTask.models;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.frm.bdTask.BirdyApp;
import com.frm.bdTask.controls.Properties;

/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public class BirdyData extends SQLiteOpenHelper {
    public static final String TAG = Properties.PREFIX + BirdyData.class.getSimpleName();
    // Declaring the Database name and default version for creating the database sqlite into the sustem directory
    // And using the content provider which will provide the accessible ability for other app can be eager to access
    // The data from this application.
    public static final String DATABASE_NAME= "BirdyNote.db";
    public static final int DATABASE_VERION= 2;
    // Defining the Columns of TodoItem Table
    public class TodoItemColumns{
        public static final String TODO_ITEM_ID                     = "_id";
        public static final String TODO_ITEM_CONTENT                ="content";
        public static final String TODO_ITEM_CREATED_AT             ="created_at";
        public static final String TODO_ITEM_STATUS                 ="status";
    }

    public class TodoColumns{
        public static final String TODO_ID                          ="_id";
        public static final String TODO_ITEM_ID                     ="todo_item_id";
        public static final String TODO_TITLE                       ="title";
        public static final String TODO_CONTENT                     ="content";
        public static final String TODO_STATUS                      ="status";
        public static final String TODO_CREATED_AT                  ="created_at";
        public static final String TODO_UPDATED_AT                  ="updated_at";
    }

    public class NoteTagColumns{
        public static final String NOTE_TAG_ID                      ="_id"; // Primary key identification
        public static final String NOTE_TAG_NAME                    ="name";
        public static final String NOTE_TAG_CONTENT                 ="content";
        public static final String NOTE_TAG_COLOR_KIND              ="color";
        public static final String NOTE_TAG_STATUS                  ="status";
        public static final String NOTE_TAG_DATE                    ="tagged_date";
    }
    protected Context mContext;
    protected BirdyApp mApplication;
    public SQLiteDatabase mDB;

    public BirdyData(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
