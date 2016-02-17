package com.ngaini.photonotes;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class BaseMenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_menu);
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
        if(id==R.id.zoo_uninstall)
        {
            //menu action for uninstalling the application
            uninstallApp();
        }

        return super.onOptionsItemSelected(item);
    }

    public void uninstallApp()
    {
        Uri package_name = Uri.parse("package:com.ngaini.photonotes");
        Intent uninstall_intent = new Intent(Intent.ACTION_DELETE, package_name);
//        uninstall_intent.setData(package_name);
        startActivity(uninstall_intent);

    }
}
