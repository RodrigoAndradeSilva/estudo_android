package com.nm.as_014_fragmentos

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var fm: FragmentManager

    private lateinit var f01: F01
    private lateinit var f02: F02

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        fm = supportFragmentManager

        // Inicializacao dos fragment F01 e F02 -> xml
        f01 = fm.findFragmentById(R.id.f01) as F01
        f01.setOnMudarTextoListener { txt ->
            f02.mudarTexto(txt)
        }
        f02 = fm.findFragmentById(R.id.f02) as F02
    }

    private fun iniActions() {

    }

    // NUNCA FAZER -> LIXO
    fun mudarTextoDeFormaPorca(txt: String) {
        f02.mudarTexto(txt)
    }
}