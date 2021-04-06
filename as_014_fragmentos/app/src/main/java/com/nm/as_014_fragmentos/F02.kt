package com.nm.as_014_fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class F02 : Fragment() {

    private lateinit var tvVO: TextView

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
        tvVO = view.findViewById(R.id.tvValorOriginal)
    }

    private fun iniActions() {

    }

    fun mudarTexto(texto: String) {
        tvVO.text = texto
    }
}