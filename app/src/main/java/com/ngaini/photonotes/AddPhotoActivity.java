package com.ngaini.photonotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
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


public class AddPhotoActivity extends ActionBarActivity {

    static final int REQUEST_IMAGE_CAPTURE=1;
    String mCurrentPhotoPath;
//    ImageView captured_imageView = (ImageView) findViewById(R.id.captured_image);
//    EditText caption_id = (EditText)findViewById(R.id.caption_editText);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        ImageView captured_imageView = (ImageView) findViewById(R.id.captured_image);
        EditText caption_id = (EditText)findViewById(R.id.caption_editText);
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
//                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
//                imagesFolder.mkdirs();
//
//                File image = new File(imagesFolder, "Image_" + timeStamp + ".png");
//                Uri uriSavedImage = Uri.fromFile(image);
//                startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
//              button disappears after taking picture
//              fab_camera.setVisibility(View.INVISIBLE);
                
                if (camera_intent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {

                        photoFile = createImageFile();
                        Log.v("WTF!!" , "path is "+photoFile.toString());
//
//                        Toast.makeText(AddPhotoActivity.this, photoFile.toString(),Toast.LENGTH_LONG);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        Log.v("WTF!!" , "path is "+photoFile.toString());
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        camera_intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(photoFile));
                        Log.v("WTF" , "path is :"+photoFile.getAbsolutePath().toString());

                        Toast.makeText(AddPhotoActivity.this, photoFile.getAbsolutePath().toString(),Toast.LENGTH_LONG);
//                        startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
                    }
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {

            // get the photo
//            Bundle extras = data.getExtras();
//            Bitmap photo = (Bitmap) extras.get("data");
//            captured_imageView.setImageBitmap(photo);

            ///
//            Bitmap yourImage = extras.getParcelable("data");
//            convert bitmap to byte
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] id = stream.toByteArray();

            // Inserting Contacts
//            Log.d("Insert: ", "Inserting ..");
//            db.addContact(new Contact("Android", imageInByte));
//            Intent i = new Intent(CameraPictureActivity.this,
//                    CameraPictureActivity.class);
//            startActivity(i);
//            finish();


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

        return super.onOptionsItemSelected(item);
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
//        Toast.makeText(AddPhotoActivity.this, mCurrentPhotoPath,Toast.LENGTH_LONG);
        Log.v("SEE THIS!!" , "path is :"+mCurrentPhotoPath);
        return image;
    }

    // save caption and image valus
    public void saveAction(MenuItem menuItem)
    {
        ImageView captured_imageView = (ImageView) findViewById(R.id.captured_image);
        EditText caption_id = (EditText)findViewById(R.id.caption_editText);

        String caption_value=caption_id.getText().toString();
        Log.v("WTF", "caption value is "+caption_value+" path"+mCurrentPhotoPath);
        Context context = AddPhotoActivity.this.getApplicationContext();
        MyDBHandler handle_db = new MyDBHandler(AddPhotoActivity.this.getApplicationContext(),null,null,1);

        //collect values
//        SQLiteDatabase db = handle_db.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(MyDBHandler.PHOTO_PATH_COLUMN, mCurrentPhotoPath);
//        values.put(MyDBHandler.PHOTO_NOTE_COLUMN, caption_value);
//        db.insert(MyDBHandler.TABLE_NAME, null,values);
//        db.close();
        handle_db.add_photo_to_db(mCurrentPhotoPath,caption_value);

    }
}
