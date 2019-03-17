package com.example.myadmin.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

public class PermissionUtils {

    public static boolean checkAndAskPermmission(
            Activity activity,
            int requestCode,
            String... permissions
    ) {
        ArrayList<String> neededPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                neededPermissions.add(permission);
            }
        }
        if (neededPermissions.size() == 0) {
            return true;
        } else {
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
        return false;
    }

}
