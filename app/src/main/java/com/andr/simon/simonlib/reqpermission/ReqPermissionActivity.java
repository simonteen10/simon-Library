package com.andr.simon.simonlib.reqpermission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by jk on 2017-01-25.
 */
public class ReqPermissionActivity extends AppCompatActivity {


    public static final int MY_PERMISSION_REQUEST_CODE = 100;

    public static final String EXTRA_PERMISSIONS = "permissions";

    private String[] permissions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        setupFromSavedInstanceState(savedInstanceState);
        checkPermissions();
    }

    private void setupFromSavedInstanceState(Bundle savedInstanceState){

        if (savedInstanceState != null) {
            permissions = savedInstanceState.getStringArray(EXTRA_PERMISSIONS);
        } else {
            Intent intent = getIntent();
            permissions = intent.getStringArrayExtra(EXTRA_PERMISSIONS);
        }
    }

    private void permissionGranted() {
        System.out.println("권한 처리 완료됨");
        finish();
        overridePendingTransition(0, 0);
    }

    private void permissionDenied(ArrayList<String> deniedpermissions) {
        System.out.println("권한처리 남음");
        finish();
        overridePendingTransition(0, 0);
    }

    private void checkPermissions(){
        ArrayList<String> needPermissions = new ArrayList<>();

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                needPermissions.add(permission);
            }

        }

        if (needPermissions.isEmpty()) {
            permissionGranted();
        } else { // //Need Request Permissions
            requestPermissions(needPermissions);
        }

    }

    public void requestPermissions(ArrayList<String> needPermissions) {
        ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), MY_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> deniedPermissions = new ArrayList<>();

        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {

                deniedPermissions.add(permission);

            }
        }

        if (deniedPermissions.isEmpty()) {
            permissionGranted();
        } else {
            permissionDenied(deniedPermissions);
        }


    }



}
