package com.frm.bdTask.models;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.frm.bdTask.BirdyApp;
import com.frm.bdTask.controls.Properties;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
        public static final String TODO_TAG_TAGID = "tag_id";

        public static final String TODO_TAG_CREATED_AT = "created_at";
        public static final String TODO_TAG_UPDATED_AT = "updated_at";
    }

    public class NoteTagColumns {
        public static final String NOTE_TAG_ID = "_id";
        public static final String NOTE_TAG_NOTEID = "note_id";
        public static final String NOTE_TAG_TAGID = "tag_id";

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
    public static final String CREATE_NOTE_TABLE = "CREATE TABLE " + NOTE_TABLE + " ( " + NoteColumns.NOTE_ID + " INTEGER " +
            " PRIMARY KEY AUTOINCREMENT, " + NoteColumns.NOTE_TITLE + " TEXT, " + NoteColumns.NOTE_CONTENT + " TEXT, " +
            NoteColumns.NOTE_COLOR_KIND + " INTEGER, " + NoteColumns.NOTE_STATUS + " INTEGER, " + NoteColumns.NOTE_CREATED + " DATETIME, " +
            NoteColumns.NOTE_UPDATED + " DATETIME " + " );";
    // SQL String for creating the TodoTag Table
    public static final String CREATE_TODO_TAG_TABLE = "CREATE TABLE " + TODO_TAG_TABLE + " ( " + TodoTagColumns.TODO_TAG_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + TodoTagColumns.TODO_TAG_TODOID + " INTEGER, " + TodoTagColumns.TODO_TAG_TAGID + " INTEGER, "
            + TodoTagColumns.TODO_TAG_CREATED_AT +
            " DATETIME, " + TodoTagColumns.TODO_TAG_UPDATED_AT + " DATETIME " + " );";
    // SQL String for creating the NoteTag Table
    public static final String CREATE_NOTE_TAG_TABLE = "CREATE TABLE " + NOTE_TAG_TABLE + " ( " + NoteTagColumns.NOTE_TAG_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + NoteTagColumns.NOTE_TAG_NOTEID + " INTEGER, " + NoteTagColumns.NOTE_TAG_TAGID + " INTEGER, "
            + NoteTagColumns.NOTE_TAG_CREATED_AT +
            " DATETIME, " + NoteTagColumns.NOTE_TAG_UPDATED_AT + " DATETIME " + ");";
    // SQL String for creating the Tag Table
    public static final String CREATE_TAG_TABLE = "CREATE TABLE " + TAG_TABLE + "( " + TagColumns.TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TagColumns.TAG_NAME + " TEXT, " + TagColumns.TAG_CONTENT + " TEXT, " + TagColumns.TAG_STATUS + " INTEGER, " + TagColumns.TAG_COLOR_KIND
            + " INTEGER, " + TagColumns.TAG_DATE + " DATETIME " + ");";

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
    public void clearTables() {
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

    /**
     * Method getTodo(). It have been used for getting the Todo record followed by particular id
     *
     * @param inTodoID The specified Todo's ID
     * @return Todo Data Object.
     */
    public Todo getTodo(long inTodoID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        final Todo todo = new Todo();
        String selectSQL = "SELECT * FROM " + TODO_TABLE + " WHERE " + TodoColumns.TODO_ID + " = " + inTodoID;

        if (db != null) {
            cursor = db.rawQuery(selectSQL, null);
            cursor.moveToFirst();
        }
        if (cursor != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                todo.setId(cursor.getInt(cursor.getColumnIndex(TodoColumns.TODO_ID)));
                todo.setTitle(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_TITLE)));
                todo.setContent(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_CONTENT)));
                todo.setStatus(cursor.getInt(cursor.getColumnIndex(TodoColumns.TODO_STATUS)));
                todo.setColorKind(cursor.getInt(cursor.getColumnIndex(TodoColumns.TODO_COLOR_KIND)));

                todo.setCreatedAt(dateFormat.parse(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_CREATED_AT))));
                todo.setUpdatedAt((dateFormat.parse(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_UPDATED_AT)))));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return todo;
    }

    /**
     * Method getTodos(). Fetching the list of all todos into the database.
     *
     * @return List of Todo Data Object.
     */
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<Todo>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String selectSQL = "SELECT * FROM " + TODO_TABLE;
        if (db != null) {
            cursor = db.rawQuery(selectSQL, null);
            if (cursor.moveToFirst()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                do {
                    try {
                        Todo todo = new Todo();
                        todo.setId(cursor.getInt(cursor.getColumnIndex(TodoColumns.TODO_ID)));
                        todo.setTitle(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_TITLE)));
                        todo.setContent(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_CONTENT)));
                        todo.setStatus(cursor.getInt(cursor.getColumnIndex(TodoColumns.TODO_STATUS)));
                        todo.setColorKind(cursor.getInt(cursor.getColumnIndex(TodoColumns.TODO_COLOR_KIND)));

                        todo.setCreatedAt((dateFormat.parse(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_CREATED_AT)))));
                        todo.setUpdatedAt((dateFormat.parse(cursor.getString(cursor.getColumnIndex(TodoColumns.TODO_UPDATED_AT)))));

                        todos.add(todo);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                } while (cursor.moveToNext());
            }
        }
        return todos;
    }

    public NoteTag getTag(long inTagId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String selectSQL = "SELECT * FROM " + TAG_TABLE + " WHERE " + TagColumns.TAG_ID + " = " + TagColumns.TAG_ID;
        NoteTag tag = new NoteTag();
        if (db != null) {
            cursor = db.rawQuery(selectSQL, null);
            if (cursor != null) {
                cursor.moveToFirst();
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    tag.setmId(cursor.getInt(cursor.getColumnIndex(TagColumns.TAG_ID)));
                    tag.setmTagName(cursor.getString(cursor.getColumnIndex(TagColumns.TAG_NAME)));
                    tag.setmContent(cursor.getString(cursor.getColumnIndex(TagColumns.TAG_CONTENT)));
                    tag.setmColorKind(cursor.getInt(cursor.getColumnIndex(TagColumns.TAG_COLOR_KIND)));
                    tag.setmStatus(cursor.getInt(cursor.getColumnIndex(TagColumns.TAG_STATUS)));

                    tag.setmTaggedDate((dateFormat.parse(cursor.getString(cursor.getColumnIndex(TagColumns.TAG_DATE)))));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return tag;
    }


    public List<NoteTag> getTags() {
        List<NoteTag> noteTags = new ArrayList<NoteTag>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String selectSQL = "SELECT * FROM " + TAG_TABLE;
        if (db != null) {
            cursor = db.rawQuery(selectSQL, null);
            if (cursor.moveToFirst()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                do {
                    try {
                        NoteTag tag = new NoteTag();
                        tag.setmId(cursor.getInt(cursor.getColumnIndex(TagColumns.TAG_ID)));
                        tag.setmTagName(cursor.getString(cursor.getColumnIndex(TagColumns.TAG_NAME)));
                        tag.setmContent(cursor.getString(cursor.getColumnIndex(TagColumns.TAG_CONTENT)));
                        tag.setmColorKind(cursor.getInt(cursor.getColumnIndex(TagColumns.TAG_COLOR_KIND)));
                        tag.setmStatus(cursor.getInt(cursor.getColumnIndex(TagColumns.TAG_STATUS)));

                        tag.setmTaggedDate((dateFormat.parse(cursor.getString(cursor.getColumnIndex(TagColumns.TAG_DATE)))));

                        noteTags.add(tag);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                } while (cursor.moveToNext());

            }
        }
        return noteTags;
    }

    public Note getNote(long inNoteId) {
        final Note note = new Note();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String selectSQL = "SELECT * FROM " + NOTE_TABLE + " WHERE " + NoteColumns.NOTE_ID + " = " + inNoteId;
        if (db != null) {
            try {
                cursor = db.rawQuery(selectSQL, null);
                if (cursor.moveToFirst()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    note.setId(cursor.getInt(cursor.getColumnIndex(NoteColumns.NOTE_ID)));
                    note.setTitle(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_TITLE)));
                    note.setContent(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_CONTENT)));

                    note.setStatus(cursor.getInt(cursor.getColumnIndex(NoteColumns.NOTE_STATUS)));
                    note.setColor(cursor.getInt(cursor.getColumnIndex(NoteColumns.NOTE_COLOR_KIND)));

                    note.setCreatedAt((dateFormat.parse(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_CREATED)))));
                    note.setUpdatedAt((dateFormat.parse(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_UPDATED)))));
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            } finally {
                db.close();
            }
        }
        return note;
    }

    public List<Note> getNotes(){
        List<Note> notes = new ArrayList<Note>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String selectSQL = "SELECT * FROM " + NOTE_TABLE;
        if(db != null){
            try{
                cursor = db.rawQuery(selectSQL, null);
                if(cursor.moveToFirst()){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    do{
                        Note note = new Note();
                        note.setId(cursor.getInt(cursor.getColumnIndex(NoteColumns.NOTE_ID)));
                        note.setTitle(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_TITLE)));
                        note.setContent(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_CONTENT)));

                        note.setStatus(cursor.getInt(cursor.getColumnIndex(NoteColumns.NOTE_STATUS)));
                        note.setColor(cursor.getInt(cursor.getColumnIndex(NoteColumns.NOTE_COLOR_KIND)));

                        note.setCreatedAt((dateFormat.parse(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_CREATED)))));
                        note.setUpdatedAt((dateFormat.parse(cursor.getString(cursor.getColumnIndex(NoteColumns.NOTE_UPDATED)))));
                        notes.add(note);
                    }while (cursor.moveToNext());
                }
            }catch (ParseException ex){
                ex.printStackTrace();
            }finally {
                db.close();
            }
        }

        return notes;
    }

}
