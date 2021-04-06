package com.nm.as_019_gps

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

fun hasGpsPermission(context: Context, permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun requestGpsPermission(activity: AppCompatActivity, permissionList: Array<String>) {
    ActivityCompat.requestPermissions(
        activity,
        permissionList,
        0
    )
}