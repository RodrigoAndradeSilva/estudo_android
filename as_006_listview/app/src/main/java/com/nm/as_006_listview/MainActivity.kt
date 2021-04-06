package com.nm.as_006_listview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context

    private lateinit var lvContatos: ListView
    private lateinit var contatos: ArrayList<String>
    private lateinit var adapterContatos: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telaincial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        lvContatos = findViewById(R.id.lvContatos)
        gerarNomes(20)
        adapterContatos = ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                contatos
        )

        lvContatos.adapter = adapterContatos
    }

    private fun gerarNomes(quantidade: Int) {
        contatos = arrayListOf()

        for( i in 1..quantidade) {
            contatos.add("Nome - $i")
        }

    }

    private fun iniActions() {
        lvContatos.setOnItemClickListener { parent, view, position, id ->
            val nome = parent.getItemAtPosition(position) as String

            Toast.makeText(
                    context,
                    nome,
                    Toast.LENGTH_SHORT
            ).show()
        }
    }
}