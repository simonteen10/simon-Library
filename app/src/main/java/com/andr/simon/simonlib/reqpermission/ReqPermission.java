package com.andr.simon.simonlib.reqpermission;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by jk on 2017-02-08.
 */
public class ReqPermission {

    Context context;
    public PermissionListener listener;
    public String[] permissions;

    public ReqPermission(Context context){
        this.context = context;
    }

    public ReqPermission setPermissionListener(PermissionListener listener) {
        this.listener = listener;
        return this;
    }

    public ReqPermission setPermissions(String... permissions){
        this.permissions = permissions;
        return this;
    }

    public void check(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Intent intent = new Intent(context, ReqPermissionActivity.class);
            intent.putExtra(ReqPermissionActivity.EXTRA_PERMISSIONS, permissions);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}