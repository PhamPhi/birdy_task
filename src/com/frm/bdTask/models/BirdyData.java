package com.frm.bdTask.models;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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
    public static final String DATABASE_NAME = "BirdyNote.db";
    public static final int DATABASE_VERION = 2;

    // Defining the Columns of TodoItem Table
    public class TodoItemColumns {
        public static final String TODO_ITEM_ID = "_id";
        public static final String TODO_ITEM_TODOID = "todo_id";
        public static final String TODO_ITEM_CONTENT = "content";
        public static final String TODO_ITEM_CREATED_AT = "created_at";
        public static final String TODO_ITEM_STATUS = "status";
    }

    public class TodoColumns {
        public static final String TODO_ID = "_id";
        public static final String TODO_TITLE = "title";
        public static final String TODO_CONTENT = "content";
        public static final String TODO_COLOR_KIND = "color";
        public static final String TODO_STATUS = "status";
        public static final String TODO_CREATED_AT = "created_at";
        public static final String TODO_UPDATED_AT = "updated_at";
    }

    public class NoteColumns {
        public static final String NOTE_ID = "_id";
        public static final String NOTE_TITLE = "title";
        public static final String NOTE_CONTENT = "content";
        public static final String NOTE_COLOR_KIND = "color";
        public static final String NOTE_STATUS = "status";
        public static final String NOTE_CREATED = "created";
        public static final String NOTE_UPDATED = "updated";
    }

    public class TodoTagColumns {
        public static final String TODO_TAG_ID = "_id";
        public static final String TODO_TAG_TODOID = "todo_id";
        public static final String TODO_TAG_TAGID  = "tag_id";

        public static final String TODO_TAG_CREATED_AT = "created_at";
        public static final String TODO_TAG_UPDATED_AT = "updated_at";
    }

    public class NoteTagColumns {
        public static final String NOTE_TAG_ID = "_id";
        public static final String NOTE_TAG_NOTEID = "note_id";
        public static final String NOTE_TAG_TAGID  = "tag_id";

        public static final String NOTE_TAG_CREATED_AT = "created";
        public static final String NOTE_TAG_UPDATED_AT = "updated_at";
    }

    public class TagColumns {
        public static final String TAG_ID = "_id"; // Primary key identification
        public static final String TAG_NAME = "name";
        public static final String TAG_CONTENT = "content";
        public static final String TAG_COLOR_KIND = "color";
        public static final String TAG_STATUS = "status";
        public static final String TAG_DATE = "tagged_date";
    }

    public static final String TODO_TABLE = "Todo";
    public static final String TODO_ITEM_TABLE = "TodoItem";
    public static final String NOTE_TABLE = "Note";
    public static final String TAG_TABLE = "Tag";
    public static final String NOTE_TAG_TABLE = "NoteTag";
    public static final String TODO_TAG_TABLE = "TodoTag";

    // SQL String for creating the TodoTable
    public static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + " ( " + TodoColumns.TODO_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + TodoColumns.TODO_TITLE + " TEXT, " + TodoColumns.TODO_CONTENT +
            " TEXT, " + TodoColumns.TODO_COLOR_KIND + " INTEGER, " + TodoColumns.TODO_STATUS + " INTEGER, " +
            TodoColumns.TODO_CREATED_AT + " DATETIME, " + TodoColumns.TODO_UPDATED_AT + " DATETIME " + " );";

    // SQL String for creating  the TodoItem table
    public static final String CREATE_TODO_ITEM_TABLE = "CREATE TABLE " + TODO_ITEM_TABLE + " ( " + TodoItemColumns.TODO_ITEM_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + TodoItemColumns.TODO_ITEM_TODOID + " INTEGER, " +
            TodoItemColumns.TODO_ITEM_CONTENT + " TEXT, " + TodoItemColumns.TODO_ITEM_CREATED_AT +
            " DATETIME, " + TodoItemColumns.TODO_ITEM_STATUS + " INTEGER " + " );";

    // SQL String for creating the Note Table
    public static final String CREATE_NOTE_TABLE = "CREATE TABLE " + NOTE_TABLE + " ( " + NoteColumns.NOTE_ID + " INTEGER "+
            " PRIMARY KEY AUTOINCREMENT, " + NoteColumns.NOTE_TITLE + " TEXT, " + NoteColumns.NOTE_CONTENT + " TEXT, " +
            NoteColumns.NOTE_COLOR_KIND + " INTEGER, " + NoteColumns.NOTE_STATUS + " INTEGER, " + NoteColumns.NOTE_CREATED + " DATETIME, " +
            NoteColumns.NOTE_UPDATED + " DATETIME " + " );";
    // SQL String for creating the TodoTag Table
    public static final String CREATE_TODO_TAG_TABLE  ="CREATE TABLE " + TODO_TAG_TABLE + " ( " + TodoTagColumns.TODO_TAG_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + TodoTagColumns.TODO_TAG_TODOID + " INTEGER, " + TodoTagColumns.TODO_TAG_TAGID +" INTEGER, "
             + TodoTagColumns.TODO_TAG_CREATED_AT +
            " DATETIME, " + TodoTagColumns.TODO_TAG_UPDATED_AT + " DATETIME " + " );";
    // SQL String for creating the NoteTag Table
    public static final String CREATE_NOTE_TAG_TABLE ="CREATE TABLE " +  NOTE_TAG_TABLE + " ( " + NoteTagColumns.NOTE_TAG_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + NoteTagColumns.NOTE_TAG_NOTEID + " INTEGER, " + NoteTagColumns.NOTE_TAG_TAGID + " INTEGER, "
            + NoteTagColumns.NOTE_TAG_CREATED_AT +
            " DATETIME, " + NoteTagColumns.NOTE_TAG_UPDATED_AT + " DATETIME " + ");";
    // SQL String for creating the Tag Table
    public static final String CREATE_TAG_TABLE      ="CREATE TABLE " + TAG_TABLE + "( " +  TagColumns.TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TagColumns.TAG_NAME + " TEXT, " + TagColumns.TAG_CONTENT + " TEXT, " + TagColumns.TAG_STATUS + " INTEGER, " + TagColumns.TAG_COLOR_KIND
            + " INTEGER, " + TagColumns.TAG_DATE + " DATETIME " +");";

    protected Context mContext;
    protected BirdyApp mApplication;
    public SQLiteDatabase mDB;

    public BirdyData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w(TAG, "BirdyData have been created...");
        db.execSQL(CREATE_TODO_ITEM_TABLE);
        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
        db.execSQL(CREATE_NOTE_TAG_TABLE);
        db.execSQL(CREATE_TODO_TAG_TABLE);
        db.execSQL(CREATE_TAG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, " Upgrading the database... ");

        // TODO :backup the data and then building the new database again and copying old data to the new data.
    }

    /**
     * Method clearTables(). It've used for removing all data from the tables which was existed into the database
     * But don't breaking the data structure.
     * After dropping the data, we must implement the onCreate() for creating the tables again!
     */
    public void clearTables(){
        Log.d(TAG, String.format("Dropping all tables into the current database [%s]", DATABASE_NAME));
        SQLiteDatabase db = this.getWritableDatabase();
        if (db != null) {
            db.execSQL(" DROP TABLE IF EXIST " + NOTE_TABLE);
            db.execSQL(" DROP TABLE IF EXIST " + TODO_TABLE);
            db.execSQL(" DROP TABLE IF EXIST " + TODO_ITEM_TABLE);

            db.execSQL(" DROP TABLE IF EXIST " + TODO_TAG_TABLE);
            db.execSQL(" DROP TABLE IF EXIST " + NOTE_TAG_TABLE);

            db.execSQL(" DROP TABLE IF EXIST " + TAG_TABLE);
            onCreate(db);
        }
    }




}
