package com.ngaini.photonotes;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Nathan on 2/15/2016.
 * cursor adapter class for populating the list view with items from the database
 */
public class NotesCursorAdapter extends CursorAdapter{


    public NotesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    /**
     *this method is for inflating the view and returning it
     * we do not ding and data to the view to this point
      */

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_list_view,parent,false);
    }

    /**
     * This Method binds all the data to the given view, in this case custom_list_view
     * such as setting a text on a text view
     * @param view
     * @param context
     * @param cursor
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView caption_textView_id;
        caption_textView_id = (TextView) view.findViewById(R.id.note_message_textView);
        String caption_value = cursor.getString(cursor.getColumnIndex(MyDBHandler.PHOTO_NOTE_COLUMN));
        String photo_location = cursor.getString(cursor.getColumnIndex(MyDBHandler.PHOTO_PATH_COLUMN));
        int i= cursor.getInt(cursor.getColumnIndex(MyDBHandler.COLUMN_ID));
//        String[] image_paths = new String[cursor.getCount()];
        Log.v("CURSOR VALUE", "cursor count :"+cursor.getCount()+" || caption "+caption_value+"|| location: "+photo_location);
//        image_paths[i++] = cursor.getString(cursor.getColumnIndexOrThrow("photo_path"));
        caption_textView_id.setText(caption_value);

    }
}
