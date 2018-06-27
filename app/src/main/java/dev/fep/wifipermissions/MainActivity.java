package dev.fep.wifipermissions;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import dev.fep.wifipermissionslib.WifiPermissions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //WifiPermissions.ShowDialog(MainActivity.this,MainActivity.this);

        Intent myIntent = new Intent(this, Testing.class);
        WifiPermissions.ShowDialogWithIntent(MainActivity.this,MainActivity.this,myIntent);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){

            WifiPermissions.ivb.setImageResource(dev.fep.wifipermissionslib.R.drawable.checkkk);

            if (WifiPermissions.isgpsenabled(this)) {

                WifiPermissions.ivc.setImageResource(dev.fep.wifipermissionslib.R.drawable.checkkk);

            }else{

                WifiPermissions.buildAlertMessageNoGps(this);
            }
        }
    }
}
