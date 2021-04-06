package com.nm.as_006_listview_simple_adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var adapterContatos: SimpleAdapter // novo tipo adaptador
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

        val De = arrayOf(HMAux.NOME, HMAux.TELEFONE) // onde esta a informacao - HMAux
        val Para = intArrayOf(R.id.tvCellNome, R.id.tvCellTelefone) // onde a informacao deve ser colocada - cell

        adapterContatos = SimpleAdapter(
                context,
                gerarContatos(30),
                R.layout.cell,
                De,
                Para
        )

        lvContatos.adapter = adapterContatos
    }

    private fun gerarContatos(quantidade: Int): ArrayList<HMAux> { // uso de um banco de dados
        val contatos = arrayListOf<HMAux>() // contatos vazia

        for (i in 1..quantidade) {
            val hmAux = HMAux() // chaves ??? - Nenhuma
            hmAux[HMAux.ID] = i.toString()
            hmAux[HMAux.NOME] = "Nome - $i"
            hmAux[HMAux.TELEFONE] = "Telefone - $i"

            contatos.add(hmAux)
        }

        return contatos
    }

    private fun iniActions() {
        lvContatos.setOnItemClickListener { parent, view, position, id ->
            val hm = parent.getItemAtPosition(position) as HMAux

            Toast.makeText(
                    context,
                    "${hm[HMAux.ID]} / position -> $position / id -> $id",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}