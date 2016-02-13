package com.ngaini.photonotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nathan on 2/12/2016.
 */
public class NotesAdapter extends ArrayAdapter<String> {


    public NotesAdapter(Context context, String[] messages) {

        //means it accepts the message string array and for each individual list view item it uses the custom adapter for the list notes
        super(context, R.layout.custom_list_view,messages);
    }

    //means that for the string that we passed in above this is this is where and how i want it to layout
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // get ready to render the layout
        LayoutInflater notes_list_infater = LayoutInflater.from(getContext());

        View customView = notes_list_infater.inflate(R.layout.custom_list_view, parent, false);

        // need a reference the to note string values and corresponding text view
        String clicked_message_value = getItem(position);

        TextView message_textView_id = (TextView)customView.findViewById(R.id.note_message_textView);

        message_textView_id.setText(clicked_message_value);
        return customView;

    }
}
