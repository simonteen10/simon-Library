package com.andr.simon.simonlib.reqpermission;

import java.util.ArrayList;

/**
 * Created by jk on 2017-02-08.
 */
public interface PermissionListener {
    void onPermissionGranted();

    void onPermissionDenied(ArrayList<String> deniedPermissions);
}
