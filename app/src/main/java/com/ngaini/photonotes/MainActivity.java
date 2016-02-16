package com.ngaini.photonotes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] messages = {" new note1 ", " blaah blaah", "raga muffin ", " haka haka", " boombastic", " cam newton ", " new note1 ", " blaah blaah", "raga muffin "};
        //convert array into list items

        MyDBHandler handler_db = new MyDBHandler(this, null, null,1);
        SQLiteDatabase db = handler_db.getWritableDatabase();
        Cursor notes_cursor = db.rawQuery("SELECT * FROM photo", null);
//        // using the custom adapter
        NotesCursorAdapter note_adapter = new NotesCursorAdapter(this,notes_cursor,0);
        ListView notes_listView = (ListView) findViewById(R.id.notes_listView);
//        //create a listView variable for the custom adapter for notes
////        ListView notes_listView = (ListView) findViewById(R.id.notes_listView);
        notes_listView.setAdapter(note_adapter);
//          populate_list();
//        // using cursor adapter for the list view
//
//
//
//        Log.v("CURSOR",""+notes_cursor.getCount());



//        notes_listView.setAdapter(note_adapter);

//        NotesCursorAdapter notes_cursor_adapter = new NotesCursorAdapter(this, notes_cursor, 0);


        // code for array adapter
//        ListAdapter list_adapter = new NotesAdapter(this, messages);
//        ListView notes_listView = (ListView) findViewById(R.id.notes_listView);
//        notes_listView.setAdapter(list_adapter);


        FloatingActionButton fab_button = (FloatingActionButton) findViewById(R.id.fab_button);

//        //Action when item from list is clicked
        notes_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //fetching the value of the item at clicked position
                String note_message = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this , note_message+" hee haw", Toast.LENGTH_SHORT).show();

            }
        });
//
//        // FAB clicked
        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this , "fab clicked", Toast.LENGTH_SHORT).show();

                // launch add photo activity
                Intent addphoto_intent = new Intent(MainActivity.this, AddPhotoActivity.class);
                startActivity(addphoto_intent);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // populate listview
    public void populate_list()
    {
        MyDBHandler handle_db = new MyDBHandler(this,null, null ,1);
        Cursor notes_cursor = handle_db.getAllRows();
        Log.v("CURSOR",""+notes_cursor.getCount());
        startManagingCursor(notes_cursor);
        String[] field_names = new String[] {MyDBHandler.PHOTO_NOTE_COLUMN };
        int[] viewIds = new int[] {R.id.note_message_textView};
        SimpleCursorAdapter note_adapter = new SimpleCursorAdapter(
                MainActivity.this,
                R.layout.custom_list_view,
                notes_cursor,
                field_names,
                viewIds
        );
        ListView notes_listView = (ListView) findViewById(R.id.notes_listView);
        notes_listView.setAdapter(note_adapter);

    }
}
