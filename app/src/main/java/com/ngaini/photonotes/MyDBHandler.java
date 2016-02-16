package com.ngaini.photonotes;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nathan on 2/13/2016.
 * DB handler class for application
 */
public class MyDBHandler extends SQLiteOpenHelper {
    Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "Photo_notes.db";
    //table name
    public static final String TABLE_NAME="photo";

    //column names
    public static final String COLUMN_ID= "_id" ;
    public static final String PHOTO_PATH_COLUMN= "photo_path" ;
    public static final String PHOTO_NOTE_COLUMN= "photo_caption";
    static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PHOTO_PATH_COLUMN+" VARCHAR(255), "+PHOTO_NOTE_COLUMN+" VARCHAR(255));";
    static final String TABLE_QUERY = "SELECT * FROM "+TABLE_NAME;


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
//        PHOTO_PATH_COLUMN = "photo_path";
//        PHOTO_NOTE_COLUMN= "photo_caption";
//        COLUMN_ID = "id";
//        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
//            String query = "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT "+PHOTO_PATH_COLUMN+" VARCHAR(255) "+PHOTO_NOTE_COLUMN+" VARCHAR(255) "+");";
//            Log.v("DB", "Database created "+query);
            db.execSQL(TABLE_CREATE);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);

    }

    // add row to database
    public void add_photo_to_db(String path, String caption)
    {
        ContentValues values = new ContentValues();
        values.put(PHOTO_PATH_COLUMN, path);
        values.put(PHOTO_NOTE_COLUMN, caption);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //print out the database
//    public String databaseToString()
//    {
//        String dbString ="";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM "+TABLE_NAME+"";
//
//
//        // cursor point to a location in the database
//        Cursor c = db.rawQuery(query, null);
//
//        //Move to the first row of the database
//        c.moveToFirst();
//        while(!c.isAfterLast())
//        {
//            if(c.getString(c.getColumnIndex("photo_path"))&&c.getString(c.getColumnIndex("photo_caption")))
//            {
//
//            }
//        }
//        db.close();
//        return dbString;
//    }

    public Cursor getAllRows()
    {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        return db.rawQuery(query, null);
    }


}
