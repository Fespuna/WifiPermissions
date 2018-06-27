# WifiPermission

This repository provides you all the required permissions to display a list of the wifi networks available arround you.
----------------

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```
  dependencies {
	        implementation 'com.github.Fespuna:WifiPermissions:1.0'
	} 
```

How to call it?

```
1)// Just dismiss the dialog
WifiPermissions.ShowDialog(MainActivity.this,MainActivity.this);

2)// Call intent to other activity when everything is right
Intent myIntent = new Intent(this, Testing.class);
WifiPermissions.ShowDialogWithIntent(MainActivity.this,MainActivity.this,myIntent);


    }

   
```

Must include this override on the activity where you call the showdialog functions to work.

```
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
```
