package com.frm.bdTask;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.frm.bdTask.controls.Properties;
import com.frm.bdTask.models.BirdyData;


/**
 * @author Larry Pham.
 * @since 3/19/14.
 */
public class BirdyProvider extends ContentProvider {

    public static final String TAG = Properties.PREFIX + BirdyProvider.class.getSimpleName();

    public static final String AUTHORITIES=  "com.frm.bdTask.contentproviders";

    private static final String NOTE_BASE_PATH = "notes";
    private static final String TODO_BASE_PATH = "todos";
    private static final String TAG_BASE_PATH  = "tags";
    private static final String TODO_ITEM_BASE_PATH= "todoitems";

    private static final String NOTE_TAG_BASE_PATH= "note_tags";
    private static final String TODO_TAG_BASE_PATH="todo_tags";


    public static final Uri BIRDY_NOTE_CONTENT_URI= Uri.parse("content://" + AUTHORITIES + "/" + NOTE_BASE_PATH);
    public static final Uri BIRDY_TODO_CONTENT_URI= Uri.parse("content://" + AUTHORITIES + "/" + TODO_BASE_PATH);
    public static final Uri BIRDY_TAG_CONTENT_URI=  Uri.parse("content://" + AUTHORITIES + "/" + TAG_BASE_PATH);
    public static final Uri TODO_ITEMS_CONTENT_URI= Uri.parse("content://" + AUTHORITIES + "/" + TODO_ITEM_BASE_PATH);

    public static final String BIRDY_NOTE_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + NOTE_BASE_PATH;
    public static final String BIRDY_TODO_CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TODO_BASE_PATH;
    public static final String BIRDY_TAG_CONTENT_TYPE  = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TAG_BASE_PATH;
    public static final String BIRDY_TODO_ITEM_CONTENT_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TODO_ITEM_BASE_PATH;

    public static final String BIRDY_NOTE_CONTENT_ITEM_TYPES = ContentResolver.CURSOR_DIR_BASE_TYPE + "/Note";
    public static final String BIRDY_TODO_CONTENT_ITEM_TYPES = ContentResolver.CURSOR_DIR_BASE_TYPE+ "/Todo";
    public static final String BIRDY_TAG_CONTENT_ITEM_TYPES = ContentResolver.CURSOR_DIR_BASE_TYPE + "/Tag";
    public static final String BIRDY_TODO_ITEM_CONTENT_ITEM_TYPES= ContentResolver.CURSOR_DIR_BASE_TYPE + "/TodoItem";

    private static final String NOTE_TABLE                                  ="Note";
    private static final String TODO_TABLE                                  ="Todo";
    private static final String TAG_TABLE                                   ="TAG";

    private static final String NOTE_TAG_TABLE                              ="NoteTag";
    private static final String TODO_TAG_TABLE                              ="TodoTag";

    private static final String TODO_ITEM_TABLE                             ="TodoItem";

    private static final int NOTE_CASE           =1;
    private static final int TODO_CASE           =2;
    private static final int TAG_CASE            =3; // Dont't need to share the tag
    private static final int NOTE_TAG_CASE       =4;
    private static final int TODO_TAG_CASE       =5;
    private static final int TODO_ITEM_CASE      =6;


    private static final int NOTE_ID_CASE= 7;
    private static final int TODO_ID_CASE= 8;
    private static final int TAG_ID_CASE= 9;
    private static final int TODO_ITEM_ID_CASE= 10;

    private static final String PARAMETER_NOTIFY = "notify";


    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        // Adding the tables for the matcher for handling the content uri
        sUriMatcher.addURI(AUTHORITIES, NOTE_BASE_PATH, NOTE_CASE);
        sUriMatcher.addURI(AUTHORITIES, TODO_BASE_PATH, TODO_CASE);
        sUriMatcher.addURI(AUTHORITIES, TAG_BASE_PATH, TAG_CASE);
        sUriMatcher.addURI(AUTHORITIES, TODO_ITEM_BASE_PATH, TODO_ITEM_CASE);

        sUriMatcher.addURI(AUTHORITIES, TODO_BASE_PATH + "/#", TODO_TAG_CASE);
        sUriMatcher.addURI(AUTHORITIES, NOTE_BASE_PATH + "/#", NOTE_TAG_CASE);

        sUriMatcher.addURI(AUTHORITIES, NOTE_TAG_BASE_PATH + "/#", NOTE_TAG_CASE);
        sUriMatcher.addURI(AUTHORITIES, TODO_TAG_BASE_PATH + "/#", TODO_TAG_CASE);

        // Adding IDs columns for content Uri
        sUriMatcher.addURI(AUTHORITIES, NOTE_BASE_PATH + "/#", NOTE_ID_CASE);
        sUriMatcher.addURI(AUTHORITIES, TODO_BASE_PATH + "/#", TODO_ID_CASE);
        sUriMatcher.addURI(AUTHORITIES, TAG_BASE_PATH + "/#", TAG_ID_CASE);
        sUriMatcher.addURI(AUTHORITIES, TODO_ITEM_BASE_PATH + "/#", TODO_ITEM_ID_CASE);
    }

    protected BirdyData mData;
    protected SQLiteDatabase mDB;
    @Override
    public boolean onCreate() {
        final Context context = getContext();
        mData = new BirdyData(context);
        mDB = mData.getWritableDatabase();

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        int code = sUriMatcher.match(uri);
        switch (code){
            case TODO_CASE:
                builder.setTables(TODO_TABLE);
                Log.d(TAG, String.format("Querying the Todo Table :%s" , TODO_TABLE));
                break;
            case NOTE_CASE:
                builder.setTables(NOTE_TABLE);
                Log.d(TAG, String.format("Querying the Note Table :%s", NOTE_TABLE));
                break;
            case TODO_ITEM_CASE:
                builder.setTables(TODO_ITEM_TABLE);
                Log.d(TAG, String.format("Querying the TodoItem table :%s", TODO_ITEM_TABLE));
                break;
            case NOTE_TAG_CASE:
                builder.setTables(NOTE_TAG_TABLE);
                Log.d(TAG, String.format("Querying the NoteTag table: %s", NOTE_TAG_TABLE));
                break;
            case TODO_TAG_CASE:
                builder.setTables(TODO_TAG_TABLE);
                Log.d(TAG, String.format("Querying the TodoTag table: %s", TODO_TAG_TABLE));
                break;
            case TAG_CASE:
                builder.setTables(TAG_TABLE);
                Log.d(TAG, String.format("Querying the Tag table: %s", TAG_TABLE));
                break;
            case TODO_ID_CASE:
                builder.appendWhere(BirdyData.TodoColumns.TODO_ID + "=" +uri.getLastPathSegment());
                Log.d(TAG, String.format("Querying the Todo table followed by the primary key: %s",
                                                                            BirdyData.TodoColumns.TODO_ID));
                break;
            case NOTE_ID_CASE:
                builder.appendWhere(BirdyData.NoteColumns.NOTE_ID + "=" + uri.getLastPathSegment());
                Log.d(TAG, String.format("Querying the Note Table followed by the primary key: %s",
                                                                            BirdyData.NoteColumns.NOTE_ID));
                break;
            case TAG_ID_CASE:
                builder.appendWhere(BirdyData.TagColumns.TAG_ID + "=" + uri.getLastPathSegment());
                Log.d(TAG, String.format("Querying the Tag Table followed by the primary key: %s",
                                                                            BirdyData.TagColumns.TAG_ID));
                break;
            case TODO_ITEM_ID_CASE:
                builder.appendWhere(BirdyData.TodoItemColumns.TODO_ITEM_ID + "=" + uri.getLastPathSegment());
                Log.d(TAG, String.format("Querying the TodoItem table followed by the primary key: %s",
                                                                            BirdyData.TodoItemColumns.TODO_ITEM_ID));
                break;
            default:
                    throw new IllegalArgumentException("Unknown Uri: " + uri);

        }
        Cursor cursor = builder.query(mDB, projection, selection, selectionArgs, null, null, sortOrder);
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
            return cursor;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }


    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        return super.bulkInsert(uri, values);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SqlArguments args = new SqlArguments(uri, selection, selectionArgs);
        mDB = mData.getWritableDatabase();
        int count = mDB.delete(args.table, args.where, args.args);
        if (count > 0) sendNotify(uri);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SqlArguments arguments = new SqlArguments(uri, selection, selectionArgs);
        mDB = mData.getWritableDatabase();
        int count = mDB.update(arguments.table, values, arguments.where, arguments.args);
        if (count > 0){
            sendNotify(uri);
        }
        return count;
    }

    private void sendNotify(Uri uri){
        String notify = uri.getQueryParameter(PARAMETER_NOTIFY);
        if(notify == null || "true".equals(notify)){
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

    /**
     * Declaring hte SQL Arguments class for parsing the data based on Uri.
     */
    static class SqlArguments{
        public final String table;
        public final String where;
        public final String[] args;

        /**
         * Configuring the Explicit Constructor with given parameters.
         * @param uri The URI Content.
         * @param where The Where Clause.
         * @param args The Argument of SQL Clause.
         */
        SqlArguments(Uri uri, String where, String[] args){
            if(uri.getPathSegments().size() == 1){
                this.table = uri.getPathSegments().get(0);
                this.where = where;
                this.args = args;
            }else if(uri.getPathSegments().size() != 2){
                throw new IllegalArgumentException("Invalid URI: " + uri);
            }else if(!TextUtils.isEmpty(where)){
                throw new UnsupportedOperationException("WHERE clause not supported " + uri);
            }else{
                this.table = uri.getPathSegments().get(0);
                this.where = "_id="+ ContentUris.parseId(uri);
                this.args = null;
            }
        }

        /**
         * Configuring the Explicit Constructor with given uri content.
         * @param uri The URI Content.
         */
        SqlArguments(Uri uri){

            if(uri.getPathSegments().size() == 1){
                table = uri.getPathSegments().get(0);
                where = null;
                args = null;
            }else{
                throw  new IllegalArgumentException("Invalid URI" + uri);
            }
        }
    }

}
