package com.nm.as_020_storage

import android.Manifest
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this

        //// Cartao Emulado
        findViewById<Button>(R.id.btnGCE).setOnClickListener {
            if (hasStoragePermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                try {
                    val extStorage = System.getenv("EXTERNAL_STORAGE")

                    writeData("Android", "$extStorage/ce", "teste.txt" )
                } catch (e: Exception) {
                    // tratativa de erro
                }
            } else {
                requestStoragePermission(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            }
        }

        findViewById<Button>(R.id.btnLCE).setOnClickListener {
            if (hasStoragePermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                try {
                    val extStorage = System.getenv("EXTERNAL_STORAGE")

                    showMessage(context,
                            readData("$extStorage/ce", "teste.txt" )
                    )
                } catch (e: Exception) {
                    // tratativa de erro
                }
            } else {
                requestStoragePermission(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            }
        }

        //// Cartao Fisico
        findViewById<Button>(R.id.btnGSD).setOnClickListener {
            if (hasStoragePermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                try {
                    val paths = getExternalFilesDirs(null)
                    val extStorage = paths[1].path

                    writeData("Android", "$extStorage/ce", "teste.txt" )

                } catch (e: Exception) {
                    // tratativa de erro
                }
            } else {
                requestStoragePermission(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            }
        }

        findViewById<Button>(R.id.btnLSD).setOnClickListener {
            if (hasStoragePermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                try {
                    val paths = getExternalFilesDirs(null)
                    val extStorage = paths[1].path

                    showMessage(context,
                            readData("$extStorage/ce", "teste.txt" )
                    )

                } catch (e: Exception) {
                    // tratativa de erro
                }
            } else {
                requestStoragePermission(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            }
        }

        findViewById<Button>(R.id.btnGSB).setOnClickListener {
            if (hasStoragePermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                try {
                    val file = filesDir
                    val extStorage = file.path

                    writeData("Android", "$extStorage/ce", "teste.txt" )

                } catch (e: Exception) {
                    // tratativa de erro
                }
            } else {
                requestStoragePermission(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            }
        }

        findViewById<Button>(R.id.btnLSB).setOnClickListener {
            if (hasStoragePermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                try {
                    val file = filesDir
                    val extStorage = file.path

                    showMessage(context,
                            readData("$extStorage/ce", "teste.txt" )
                    )

                } catch (e: Exception) {
                    // tratativa de erro
                }
            } else {
                requestStoragePermission(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            }
        }
    }

    private fun iniActions() {

    }
}