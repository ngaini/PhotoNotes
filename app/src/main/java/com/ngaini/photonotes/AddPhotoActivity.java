package com.ngaini.photonotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.menu.MenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class AddPhotoActivity extends ActionBarActivity {
    File external_picture_directory;

    Uri image_uri;
    boolean image_taken;
    private static String image_path;
    static final int REQUEST_IMAGE_CAPTURE=11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        //action for the FAB add photo button
        final FloatingActionButton fab_camera = (FloatingActionButton) findViewById(R.id.fab_addPhoto_button);
        //disable the button if no camera available
        if(!hasCamera())
        {
            fab_camera.setEnabled(false);
            Toast.makeText(AddPhotoActivity.this, "No camera on device", Toast.LENGTH_SHORT);
        }
        Toast.makeText(AddPhotoActivity.this, "Houston we have a camera on device", Toast.LENGTH_SHORT);
        fab_camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (camera_intent.resolveActivity(getPackageManager()) != null) {

                    external_picture_directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    Random random_generator = new Random();
                    int random_number = random_generator.nextInt(10000);
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    String image_name = "IMG_"+ random_number + timeStamp;
                    File image_file = new File(external_picture_directory, image_name + ".jpg");
                    image_uri = Uri.fromFile(image_file);
                    image_path = image_file.getAbsolutePath();
                    camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
                    startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

    }

    //check if device has camera
    public boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);

    }


    //return image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if(requestCode == REQUEST_IMAGE_CAPTURE){
            //Now you can do whatever you want to depending upon whether the user ticks right or wrong option

            //Do something if the user hits the OK button
            if(resultCode == RESULT_OK){
                //Do something if you want to do with the achieved result
                //Fetch the image from the External Storage
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 4;
                Bitmap b = BitmapFactory.decodeFile(image_path, options);

                //Display the captured image inside the ImageView
                ImageView image_value = (ImageView)findViewById(R.id.captured_image);
                image_value.setImageBitmap(b);
                image_taken = true;
            }
            if(resultCode == RESULT_CANCELED){
                //This will not allow the user to save the image after selecting to not capture the image
                Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
                image_taken=false;
            }


        }


}




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_photo, menu);
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
        if(id==R.id.zoo_uninstall)
        {
            //menu action for uninstalling the application
            uninstallApp();
        }
        return super.onOptionsItemSelected(item);
    }


//        file creation try #1
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "IMG_" + timeStamp + "_";
//        File storageDir = Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
////        Toast.makeText(AddPhotoActivity.this, mCurrentPhotoPath,Toast.LENGTH_LONG);
////        Log.v("SEE THIS!!" , "path is :"+mCurrentPhotoPath);
//        setPath(image.getAbsolutePath());
//        return image;
//    }



    //

    // save caption and image valus
    public void saveAction(MenuItem menuItem)
    {
        if(image_taken)
        {
            ImageView captured_imageView = (ImageView) findViewById(R.id.captured_image);
            EditText caption_id = (EditText)findViewById(R.id.caption_editText);

            String caption_value;
            if(caption_id.getText().toString().trim().length()>0)
            {
                caption_value =caption_id.getText().toString();
            }
            else
            {
                String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
                caption_value = "Image taken on "+timeStamp;
            }
            Log.v("WTF", "caption value is "+caption_value+" path"+image_path);
            Context context = AddPhotoActivity.this.getApplicationContext();
            MyDBHandler handle_db = new MyDBHandler(AddPhotoActivity.this.getApplicationContext(),null,null,1);

            //collect values
//        SQLiteDatabase db = handle_db.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(MyDBHandler.PHOTO_PATH_COLUMN, mCurrentPhotoPath);
//        values.put(MyDBHandler.PHOTO_NOTE_COLUMN, caption_value);
//        db.insert(MyDBHandler.TABLE_NAME, null,values);
//        db.close();
            handle_db.add_photo_to_db(image_path,caption_value);
            Intent move_to_main = new Intent(AddPhotoActivity.this, MainActivity.class);
            finish();
            startActivity(move_to_main);
        }
        else
        {
            Toast.makeText(AddPhotoActivity.this,"No image to save",Toast.LENGTH_LONG);
        }
    }

//    public void setPath(String path_val)
//    {
//        photo_path_value = path_val;
//    }
//
//    public String getPath()
//    {
//        return photo_path_value;
//    }
    public void uninstallApp()
    {
        Uri package_name = Uri.parse("package:com.ngaini.photonotes");
        Intent uninstall_intent = new Intent(Intent.ACTION_DELETE, package_name);
    //        uninstall_intent.setData(package_name);
        startActivity(uninstall_intent);

    }
}
