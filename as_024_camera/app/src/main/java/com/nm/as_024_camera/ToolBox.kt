package com.nm.as_024_camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File

fun criarDiretorio(local: String) {
    val diretorio = File(local)
    if (!diretorio.exists()) {
        diretorio.mkdir()
    }
}

fun hasStoragePermission(context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

fun requestStoragePermission(activity: AppCompatActivity) {
    val permissionList = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    ActivityCompat.requestPermissions(
        activity,
        permissionList,
        0
    )
}