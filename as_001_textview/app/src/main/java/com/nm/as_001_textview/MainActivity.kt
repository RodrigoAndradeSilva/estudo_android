package com.nm.as_001_textview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvAndroidNew: TextView
    private lateinit var tvIosNew: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)

        tvAndroidNew = findViewById(R.id.tvAndroid)
        tvIosNew = findViewById(R.id.tvIos)

        tvAndroidNew.setTextColor(resources.getColor(R.color.vermelho_paulista))
        tvAndroidNew.text = "Novo Texto"
    }
}