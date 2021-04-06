package com.nm.as_015_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

class F02 : Fragment() {

    private lateinit var lvNomes: ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f02, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniVars(view)
        iniActions()
    }

    private fun iniVars(view: View) {
        lvNomes = view.findViewById(R.id.lvNomes)
        lvNomes.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            montarListaNomes(50)
        )
    }

    private fun montarListaNomes(quantidade: Int): ArrayList<String> {
        val nomes = arrayListOf<String>() // lista vazia

        for (i in 1..quantidade) {
            nomes.add("Nome - $i")
        }

        return nomes
    }

    private fun iniActions() {
        lvNomes.setOnItemClickListener { parent, view, position, id ->
            val nome = parent.getItemAtPosition(position) as String

            delegateNome?.invoke(nome)
        }
    }

    private var delegateNome: ((txt: String) -> Unit)? = null

    fun setOnNameSelectedListener(delegateNome: ((txt: String) -> Unit)?) {
        this.delegateNome = delegateNome
    }

}