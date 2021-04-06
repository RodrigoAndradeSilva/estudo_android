package com.nm.as_006_listview_baseadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterContatos(
        private val context: Context,
        private val resource: Int,
        private val data: List<HMAux>
) : BaseAdapter() {

    // ler o xml e transforma em binario findViewById
    private val mInflater = LayoutInflater.from(context)

//    private val mInflater =
//            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // Extra -> Demonstracao saber quantas celulas no total foram criadas pelo getView
    private var contador = 0

    // devolve a quantidade de registros da minha colecao
    override fun getCount(): Int {
        return data.size
    }

    // devolve um item da minha colecao referenciada pelo parametro position
    override fun getItem(position: Int): Any {
        return data[position]
    }

    // id ??? PK A2040B (NAO SERVE DE NADA) -> PK 1,1 ( [idpedido,idpedidoitem]) -> (NAO SERVE DE NADA)
    // id PK (Inteiro Long) e composta somente de 1 campo (VALIDO)
    override fun getItemId(position: Int): Long {
        return data[position][HMAux.ID]!!.toLong()
    }

    //
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var novaCelula = false
        var mView = convertView

        if (mView == null) {
            novaCelula = true
            contador++

            mView = mInflater.inflate(resource, parent, false)
        }

        // Acessar os elementos dentro dessa view
        val tvNome = mView?.findViewById<TextView>(R.id.tvCellNome)
        val tvTelefone = mView?.findViewById<TextView>(R.id.tvCellTelefone)
        // extra
        val tvContador = mView?.findViewById<TextView>(R.id.tvCellContador)

        // Acessar o meu registro
        //val hmAux = getItem(position) as HMAux
        val hmAux = data[position]

        // juntar as coisas
        tvNome?.text = hmAux[HMAux.NOME]
        tvTelefone?.text = hmAux[HMAux.TELEFONE]

        if (novaCelula) {
            tvContador?.text = contador.toString()
        }

        return mView!!
    }
}