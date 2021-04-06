package com.nm.as_016_drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FOptions : Fragment() {

    private lateinit var btnAtivar: Button

    private var indice = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.foptions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniVars(view)
        iniActions()
    }

    private fun iniVars(view: View) {
        btnAtivar = view.findViewById(R.id.btnAtivar)
    }

    private fun iniActions() {
        btnAtivar.setOnClickListener {
            delegateAtivar?.invoke("Android - ${++indice}")
        }
    }

    private var delegateAtivar: ((txt: String) -> Unit)? = null

    fun setOnAtivarListener(delegateAtivar: ((txt: String) -> Unit)?) {
        this.delegateAtivar = delegateAtivar
    }

}