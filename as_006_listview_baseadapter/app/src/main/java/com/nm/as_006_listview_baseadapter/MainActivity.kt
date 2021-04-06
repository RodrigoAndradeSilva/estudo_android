package com.nm.as_006_listview_baseadapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context

    private lateinit var lvContatos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        lvContatos = findViewById(R.id.lvContatos)

        lvContatos.adapter = AdapterContatos(
                context,
                R.layout.cell,
                gerarContatos(1000)
        )
    }

    private fun gerarContatos(quantidade: Int): List<HMAux> {
        val contatos = arrayListOf<HMAux>() // lista VAZIA

        for (i in 1..quantidade) {
            val hmAux = HMAux() // vazio - sem chaves
            hmAux[HMAux.ID] = i.toString()
            hmAux[HMAux.NOME] = "Nome - $i"
            hmAux[HMAux.TELEFONE] = "Telefone - $i"

            contatos.add(hmAux)
        }

        return contatos
    }

    private fun iniActions() {

    }
}