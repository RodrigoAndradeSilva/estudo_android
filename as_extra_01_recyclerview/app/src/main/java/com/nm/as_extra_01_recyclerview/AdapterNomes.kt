package com.nm.as_extra_01_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterNomes(
    private val context: Context,
    private val resource: Int,
    private val data: List<String>,
    private var itemSelected: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Criar VH findViewByID
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(resource, parent, false)

        return DefaultViewHolder(mView)

    }

    // Junta dados na ViewHolder
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // recuperando os dados de uma posicao especifica. Aquela referenciada pelo parametro position
        val item = data[position]

        val dfVH = holder as DefaultViewHolder
        dfVH.tvNome.text = item

    }

    override fun getItemCount(): Int {
        return data.size
    }

    // Classes de ViewHolder
    private inner class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView = itemView.rootView
        val tvNome: TextView = itemView.findViewById(android.R.id.text1)

        init {
            mView.setOnClickListener {
                val position = adapterPosition
                val item = data[position]

                itemSelected.invoke(item)
            }
        }
    }
}