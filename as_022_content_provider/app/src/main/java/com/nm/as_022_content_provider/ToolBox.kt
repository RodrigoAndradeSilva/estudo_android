package com.nm.as_022_content_provider

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

fun hasContactsPermission(context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED
}

fun requestContactsPermission(activity: AppCompatActivity) {
    val permissionList = arrayOf(
        Manifest.permission.READ_CONTACTS
    )

    ActivityCompat.requestPermissions(
        activity,
        permissionList,
        0
    )
}

fun showMessage(context: Context, texto: String) {
    Toast.makeText(
        context,
        texto,
        Toast.LENGTH_SHORT
    ).show()
}