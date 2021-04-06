package com.nm.as_004_imageview

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var ivCamera: ImageView
    private lateinit var tvIndice: TextView
    private lateinit var ivM: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        ivCamera = findViewById(R.id.ivCamera)
        tvIndice = findViewById(R.id.tvIndice)
        ivM = findViewById(R.id.ivM)

        Log.d("TAMANHO", "${ivCamera.width} - ${ivCamera.height}")
    }

    private fun iniActions() {
        tvIndice.setOnClickListener {
            Log.d("TAMANHO", "${ivCamera.width} - ${ivCamera.height}")

            ivCamera.setImageResource(R.drawable.ic_alert)
            // arquivo que eu fiz download
//            ivCamera.setImageBitmap(
//                BitmapFactory.decodeFile("/sdcard/foto.jpg") // estouro de memoria
//            )
            ivCamera.setImageBitmap(null)
        }

        ivM.setOnClickListener {
            ivCamera.setImageResource(R.drawable.ic_camera)
        }
    }
}