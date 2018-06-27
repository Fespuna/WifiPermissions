package dev.fep.wifipermissionslib;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Ferran on 27/6/18.
 */

public class WifiPermissions {

    public static ImageView ivb,ivc;
    static int stop = 0;
    public static void ShowDialog(final Activity a,final Context c) {

            final Dialog dialog = new Dialog(c);
            dialog.setContentView(R.layout.dialog);

            dialog.setCancelable(false);
            //dialog.setTitle("REQUIREMENTS BEFORE USE THIS APP");

            ImageView iva = (ImageView) dialog.findViewById(R.id.iva);
            ivb = (ImageView) dialog.findViewById(R.id.ivb);
            ivc = (ImageView) dialog.findViewById(R.id.ivc);





            LinearLayout continuebutton = (LinearLayout) dialog.findViewById(R.id.continuebutton);
            // if button is clicked, close the custom dialog
            continuebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(iswifienabled(c)){

                        if (isWifiPermissionGranted(a)) {

                            ivb.setImageResource(R.drawable.checkkk);


                            if(isgpsenabled(c)){

                                // TOT CORRECTE
                                dialog.dismiss();

                            }else{
                                buildAlertMessageNoGps(c);
                            }

                        }

                    }else{

                        WifiManager wifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
                        wifiManager.setWifiEnabled(true);

                    }

                }
            });

        if(!iswifienabled(c)||!justcheckifwifipermissiongranted(a)||!isgpsenabled(c)){

            dialog.show();


            if (iswifienabled(c)) {

                iva.setImageResource(R.drawable.checkkk);

            }else{

                WifiManager wifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                iva.setImageResource(R.drawable.checkkk);

            }

            if (isWifiPermissionGranted(a)) {

                ivb.setImageResource(R.drawable.checkkk);


                if (isgpsenabled(c)) {

                    ivc.setImageResource(R.drawable.checkkk);

                }else{

                    buildAlertMessageNoGps(c);

                }

            }

        }




    }


    public static void ShowDialogWithIntent(final Activity a,final Context c, final Intent nextactivityintent) {

        final Dialog dialog = new Dialog(c);
        dialog.setContentView(R.layout.dialog);

        dialog.setCancelable(false);
        //dialog.setTitle("REQUIREMENTS BEFORE USE THIS APP");

        ImageView iva = (ImageView) dialog.findViewById(R.id.iva);
        ivb = (ImageView) dialog.findViewById(R.id.ivb);
        ivc = (ImageView) dialog.findViewById(R.id.ivc);




        LinearLayout continuebutton = (LinearLayout) dialog.findViewById(R.id.continuebutton);
        // if button is clicked, close the custom dialog
        continuebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iswifienabled(c)){

                    if (isWifiPermissionGranted(a)) {

                        ivb.setImageResource(R.drawable.checkkk);


                        if(isgpsenabled(c)){

                            // TOT CORRECTE



                            a.startActivity(nextactivityintent);


                           // dialog.dismiss();

                        }else{
                            buildAlertMessageNoGps(c);
                        }

                    }

                }else{

                    WifiManager wifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
                    wifiManager.setWifiEnabled(true);

                }

            }
        });

        if(!iswifienabled(c)||!justcheckifwifipermissiongranted(a)||!isgpsenabled(c)){

            dialog.show();


            if (iswifienabled(c)) {

                iva.setImageResource(R.drawable.checkkk);

            }else{

                WifiManager wifiManager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                iva.setImageResource(R.drawable.checkkk);

            }

            if (isWifiPermissionGranted(a)) {

                ivb.setImageResource(R.drawable.checkkk);


                if (isgpsenabled(c)) {

                    ivc.setImageResource(R.drawable.checkkk);

                }else{

                    buildAlertMessageNoGps(c);

                }

            }

        }else{
            a.startActivity(nextactivityintent);

        }




    }


    public static boolean iswifienabled(Context c) {

        WifiManager wifi = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean justcheckifwifipermissiongranted(Activity a){
        if (Build.VERSION.SDK_INT >= 23) {
            if (a.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //   Log.v(TAG,"Permission is granted");
               /* if (isWifiPermissionGranted(a)) {

                    ivb.setImageResource(R.drawable.checkkk);

                }*/
                return true;
            } else {

                //  Log.v(TAG,"Permission is revoked");
               // ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //   Log.v(TAG, "Permission is granted");
            return true;
        }
    }


    public static boolean isWifiPermissionGranted(Activity a) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (a.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //   Log.v(TAG,"Permission is granted");
               /* if (isWifiPermissionGranted(a)) {

                    ivb.setImageResource(R.drawable.checkkk);

                }*/
                return true;
            } else {

                //  Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //   Log.v(TAG, "Permission is granted");
            return true;
        }


    }


    public static boolean isgpsenabled(Context c) {

        final LocationManager manager = (LocationManager) c.getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            return false;
        }else{

            ivc.setImageResource(R.drawable.checkkk);
            return  true;
        }
    }


    public static void buildAlertMessageNoGps(final Context c) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setMessage(c.getResources().getString(R.string.askforgps))
                .setCancelable(false)
                .setPositiveButton(c.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {

                        final Handler handler = new Handler();


                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                if(isgpsenabled(c)){
                                    ivc.setImageResource(R.drawable.checkkk);
                                    handler.removeCallbacksAndMessages(null);
                                    stop=1;
                                }else{

                                }

                                if (stop!=1) {
                                    handler.postDelayed(this, 1000);
                                }
                            }
                        }, 1000);





                        c.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));


                    }
                })
                .setNegativeButton(c.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();


        alert.show();

    }


}
