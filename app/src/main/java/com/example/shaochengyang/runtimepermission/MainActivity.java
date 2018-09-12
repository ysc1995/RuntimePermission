package com.example.shaochengyang.runtimepermission;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        Log.d(TAG, "onCreate:"+ android.os.Build.VERSION.SDK_INT);
        Log.d(TAG, "onCreate:"+ android.os.Build.VERSION_CODES.LOLLIPOP);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(MainActivity.this, "permission granted", Toast.LENGTH_LONG).show();

                    } else {
                        requestSMSPermission();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "older version permission granted", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void requestSMSPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(
                MainActivity.this,Manifest.permission.READ_SMS)){//if user decline permission

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Permission Title");
            builder.setMessage("If you don't grante this permission, you wont receive sms");
            builder.setPositiveButton("Grante", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,new String[]{Manifest.permission.READ_SMS},1234);
                }
            });

            builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();

        }
        else{
            ActivityCompat.requestPermissions(
                    MainActivity.this,new String[]{Manifest.permission.READ_SMS},1234);
        }
    }
}
