package com.nm.as_extra_01_recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var adapterNomes: AdapterNomes
    private lateinit var rvNomes: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        rvNomes = findViewById(R.id.rvNomes)
        rvNomes.layoutManager = LinearLayoutManager(context)

        montarLista()
    }

    private fun montarLista() {
        adapterNomes = AdapterNomes(
            context,
            android.R.layout.simple_list_item_1,
            colecao(100)
        ) {item ->
            Toast.makeText(
                context,
                item,
                Toast.LENGTH_SHORT
            ).show()

        }
        rvNomes.adapter = adapterNomes
    }

    private fun colecao(quantidade: Int): ArrayList<String> {
        val colecao = arrayListOf<String>()

        for (i in 1..quantidade) {
            colecao.add("Nome - $i")
        }

        return colecao
    }

    private fun iniActions() {

    }
}