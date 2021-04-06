package com.nm.as_007_ciclo_de_vida

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var valorArmazenado: String

    private lateinit var etTexto: EditText
    private lateinit var btnMOV: Button
    private lateinit var btnMUV: Button

    // quando a tela é criada do zero o valor desse parametro é null
    // quando a tela é criada em funcao de uma mudanca de configurado esse parametro
    // devolvera uma estrutura nao nula

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars(savedInstanceState)
        iniActions()

        logar(valor = "onCreate")
    }

    override fun onStart() {
        super.onStart()

        logar(valor = "onStart")
    }

    override fun onResume() {
        super.onResume()

        logar(valor = "onResume")
    }

    override fun onPause() {
        super.onPause()

        logar(valor = "onPause")
    }


    override fun onStop() {
        super.onStop()

        logar(valor = "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        logar(valor = "onDestroy")
    }

    private fun iniVars(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            // mudanca de configuracao
            valorArmazenado = savedInstanceState.getString("va")!!
        } else {
            // tela nova
            valorArmazenado = "Valor Original"
        }

        context = this
        etTexto = findViewById(R.id.etTexto)
        btnMOV = findViewById(R.id.btnMOV)
        btnMUV = findViewById(R.id.btnMUV)
    }

    private fun iniActions() {
        btnMUV.setOnClickListener {
            valorArmazenado = etTexto.text.toString().trim()
        }

        btnMOV.setOnClickListener {
            Toast.makeText(
                context,
                valorArmazenado,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // cache
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("va", valorArmazenado)

        logar(valor = "onSaveInstanceState")
    }

    private fun logar(tag: String = "CICLO_IMPACTA", valor: String) {
        Log.d(tag, valor)
    }

}