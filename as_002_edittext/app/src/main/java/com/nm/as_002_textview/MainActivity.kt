package com.nm.as_002_textview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context // ponte entre o seu app recursos do S.O.

    private lateinit var etNome: EditText
    private lateinit var etIdade: EditText
    private lateinit var btnMV: Button
    private lateinit var btnIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        etNome = findViewById(R.id.etNome)
        etIdade = findViewById(R.id.etIdade)
        btnMV = findViewById(R.id.btnMV)
        btnIV = findViewById(R.id.btnIV)
    }

    private fun iniActions() {
        btnMV.setOnClickListener {
            //val valorNome = etNome.text.toString()
            //val valorIdade = etIdade.text.toString()

            Toast.makeText(
                context,
                "${etNome.text} - ${etIdade.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnIV.setOnClickListener {
            Toast.makeText(
                context,
                "Ó Nóis aqui!!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}