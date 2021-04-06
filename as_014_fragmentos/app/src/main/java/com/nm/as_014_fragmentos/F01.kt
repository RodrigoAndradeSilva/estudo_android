package com.nm.as_014_fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment

class F01 : Fragment() {

    private lateinit var cbAndroid: CheckBox
    private lateinit var btnAtivar: Button

    // NUNCA FAZER -> LIXO
    //private lateinit var host: MainActivity

    private var indice = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f01, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniVars(view)
        iniActions()
    }

    private fun iniVars(view: View) {
        // NUNCA FAZER -> LIXO
        //host = activity as MainActivity

        cbAndroid = view.findViewById(R.id.cbAndroid)
        btnAtivar = view.findViewById(R.id.btnAtivar)
    }

    private fun iniActions() {
        btnAtivar.setOnClickListener {
            if (cbAndroid.isChecked) {
                mudouOTexto?.invoke("Sei Android - ${++indice}")

                // NUNCA FAZER -> LIXO
                //host.mudarTextoDeFormaPorca("Sei Android - ${++indice}")
            } else {
                Toast.makeText(
                    requireContext(),
                    "Não sei Android",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private var mudouOTexto: ((txt: String) -> Unit)? = null

    fun setOnMudarTextoListener(mudouOTexto: ((txt: String) -> Unit)?) {
        this.mudouOTexto = mudouOTexto
    }

}