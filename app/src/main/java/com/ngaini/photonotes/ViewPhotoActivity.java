package com.ngaini.photonotes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;


public class ViewPhotoActivity extends BaseMenuActivity {
    String values1,values2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {

            values1 = extras.getString("caption_value");
            values2 = extras.getString("path_value");
        }
        populate_view(values1, values2);
    }

    private void populate_view(String caption, String path) {
        ImageView imageView_id = (ImageView)findViewById(R.id.view_image);
        TextView  caption_id =(TextView) findViewById(R.id.view_caption_textView);

        Log.v("CONFIRMATION", "caption: "+caption+" || path:"+path);



        caption_id.setText(caption);
//


        Uri uriFromPath = Uri.fromFile(new File(path));

        Bitmap  b=null;

//            b = BitmapFactory.decodeStream(getContentResolver().openInputStream(uriFromPath));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            b = BitmapFactory.decodeFile(path, options);

        imageView_id.setImageBitmap(b);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_view_photo, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
