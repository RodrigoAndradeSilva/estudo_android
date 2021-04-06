package com.nm.as_021_shared_preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context

    private lateinit var btnMV: Button
    private lateinit var btnSomar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this

        btnMV = findViewById(R.id.btnMV)
        btnSomar = findViewById(R.id.btnSomar)
    }

    private fun iniActions() {
        btnMV.setOnClickListener {
            val contador = getPreferenceContador(context) // -1
            showMessage(context, contador.toString())

            val produto = getPreferenceProduto(context)
            showMessage(context, "${produto.nome} -> ${produto.proceUnitario}")
        }

        btnSomar.setOnClickListener {
            val contador = getPreferenceContador(context) // -1
            setPreferenceContador(context, contador + 1)

            val produto = getPreferenceProduto(context)
            if (produto.idProduto == -1L) {
                produto.idProduto = 10L
                produto.nome = "Produto - ${produto.idProduto}"
                produto.proceUnitario = 0.5
            } else {
                produto.proceUnitario =  produto.proceUnitario * 2
            }
            setPreferenceProduto(context, produto)
        }
    }
}