package com.ngaini.photonotes;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nathan on 2/13/2016.
 * DB handler class for application
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "photo_notes.db";
    //table name
    public static final String TABLE_NAME="photo";

    //column names
    public static final String COLUMN_ID = "id";
    public static final String PHOTO_PATH_COLUMN = "photo_path";
    public static final String PHOTO_NOTE_COLUMN= "photo_caption";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+" ("+COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT "+PHOTO_PATH_COLUMN+" TEXT "+PHOTO_NOTE_COLUMN+" TEXT "+");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);

    }

    // add row to database
    public void add_photo_to_db(Photo newPhoto)
    {
        ContentValues values = new ContentValues();
        values.put(PHOTO_PATH_COLUMN, newPhoto.getPath());
        values.put(PHOTO_NOTE_COLUMN, newPhoto.getCaption());

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
}
