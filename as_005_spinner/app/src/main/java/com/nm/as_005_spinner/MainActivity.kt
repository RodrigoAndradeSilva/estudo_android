package com.nm.as_005_spinner

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var btnMV: Button

    // Spinner Educacional
    private lateinit var spNomes: Spinner // combobox
    private lateinit var nomes: ArrayList<String> // colecao
    private lateinit var adapterNomes: ArrayAdapter<String>   // gerar celula para utilizacao do controle

    // Spinner Produtos
    private lateinit var spProdutos: Spinner // combobox
    private lateinit var produtos: ArrayList<Produto> // colecao
    private lateinit var adapterProdutos: ArrayAdapter<Produto>   // gerar celula para utilizacao do controle

    // Spinner Produtos HM
    private lateinit var spProdutosHM: Spinner // combobox
    private lateinit var produtosHM: ArrayList<HmAux> // colecao
    private lateinit var adapterProdutosHM: ArrayAdapter<HmAux>   // gerar celula para utilizacao do controle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        btnMV = findViewById(R.id.btnMV)

        // Spinner Educacional
        spNomes = findViewById(R.id.spNomes) // inicializei o spinner a partir do xml
        gerarNomes(100) // fonte geradora de dados

        adapterNomes = ArrayAdapter( // fabrica de celulas
                context,
                android.R.layout.simple_spinner_item, // spinner fechado
                nomes
        )

        adapterNomes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // spinner aberto

        spNomes.adapter = adapterNomes

        spNomes.setSelection(5)

        // Spinner Produtos
        spProdutos = findViewById(R.id.spProdutos)
        gerarProdutos(100)

        adapterProdutos = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                produtos
        )

        adapterProdutos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProdutos.adapter = adapterProdutos
        spProdutos.setSelection(findCode(spProdutos, 150)) // completamento inutil

        // Spinner Produtos HM
        spProdutosHM = findViewById(R.id.spProdutosHM)
        gerarProdutosHM(100)

        adapterProdutosHM = ArrayAdapter(
                context,
                android.R.layout.simple_spinner_item,
                produtosHM
        )

        adapterProdutosHM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spProdutosHM.adapter = adapterProdutosHM
    }

    private fun findCode(spAux: Spinner, code: Long): Int {
        for (i in 0 until spAux.adapter.count) {
            val pAux = spAux.getItemAtPosition(i) as Produto // peguei o produto da posicao do indice i
            if (pAux.idProduto == code) {
                return i
            }
        }

        return 0
    }

    // Spinner Educacional
    private fun gerarNomes(quantidade: Int) {
        nomes = arrayListOf() // colecao vazio porem não nula

        for (i in 1 until quantidade) {
            nomes.add("Nome - ${i + 99}")
        }
    }

    // Spinner Produtos
    private fun gerarProdutos(quantidade: Int) {
        produtos = arrayListOf() // colecao vazio porem não nula

        for (i in 1 until quantidade) {
            produtos.add(
                    Produto(
                            (i + 99).toLong(),
                            "Produto - ${i + 99}",
                            i * 2.0
                    )
            )
        }
    }

    // Spinner Produtos HM
    private fun gerarProdutosHM(quantidade: Int) {
        produtosHM = arrayListOf() // colecao vazio porem não nula

        for (i in 1 until quantidade) {
            produtosHM.add(
                    Produto(
                            (i + 99).toLong(),
                            "Produto - ${i + 99}",
                            i * 2.0
                    ).toHashMap()
            )
        }
    }

    private fun iniActions() {
        btnMV.setOnClickListener {
            // Spinner Educacional
            val nome = spNomes.selectedItem as String

            // Spinner Produtos
            val pAux = spProdutos.selectedItem as Produto

            Toast.makeText(
                    context,
                    pAux.idProduto.toString(),
                    Toast.LENGTH_SHORT
            ).show()
        }

        spProdutos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val pAux = parent?.getItemAtPosition(position) as Produto

                Toast.makeText(
                        context,
                        pAux.idProduto.toString(),
                        Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }


}