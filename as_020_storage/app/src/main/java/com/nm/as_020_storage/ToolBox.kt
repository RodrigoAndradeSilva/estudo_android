package com.nm.as_020_storage

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.lang.StringBuilder

//region Permission
fun hasStoragePermission(context: Context, permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

fun requestStoragePermission(activity: AppCompatActivity, permissionList: Array<String>) {
    ActivityCompat.requestPermissions(
        activity,
        permissionList,
        0
    )
}
//endregion

//region Storage
fun writeData(txt: String, path: String, fileName: String) {
    val diretorio = File(path)
    if (!diretorio.exists()) {
        diretorio.mkdir()
    }

    val f: FileWriter

    try {
        f = FileWriter(
            "$path/$fileName",
            true
        )

        f.write(txt)
        f.flush()
        f.close()
    } catch (e: Exception) {
        // tratativa de erro
    }
}

fun readData(path: String, fileName: String): String {
    val conteudo = StringBuilder()

    val diretorio = File(path)
    if (!diretorio.exists()) {
        return "Erro: diretorio nao existe!!!"
    }

    val arquivo = File("$path/$fileName")
    if (!arquivo.exists()) {
        return "Erro: nao nao existe!!!"
    }

    try {
        val input = BufferedReader(
            FileReader(
                arquivo
            )
        )

        var linha: String?

        while (true) {
            linha = input.readLine()

            if (linha != null) {
                conteudo.append(linha)
            } else {
                break
            }
        }

    } catch (e: Exception) {
        // tratar erro
    }

    return conteudo.toString()
}
//endregion

fun showMessage(context: Context, msg: String) {
    Toast.makeText(
        context,
        msg,
        Toast.LENGTH_SHORT
    ).show()
}