package com.ngaini.photonotes;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] messages = {" new note1 ", " blaah blaah", "raga muffin ", " haka haka", " boombastic", " cam newton ", " new note1 ", " blaah blaah", "raga muffin "};
        //convert array into list items
//        ListAdapter list_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);

        // using the custom adapter
        ListAdapter list_adapter = new NotesAdapter(this, messages);

        //create a listView variable for the custom adapter for notes
        ListView notes_listView = (ListView) findViewById(R.id.notes_listView);
        notes_listView.setAdapter(list_adapter);

        FloatingActionButton fab_button = (FloatingActionButton) findViewById(R.id.fab_button);

        //Action when item from list is clicked
        notes_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //fetching the value of the item at clicked position
                String note_message = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this , note_message+" hee haw", Toast.LENGTH_SHORT).show();

            }
        });

        // FAB clicked
        fab_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this , "fab clicked", Toast.LENGTH_SHORT).show();

                // launch add photo activity
                Intent addPhotoIntent = new Intent(MainActivity.this, AddPhotoActivity.class);
                startActivity(addPhotoIntent);
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
}
