package com.nm.as_015_viewpager

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
        cbAndroid = view.findViewById(R.id.cbAndroid)
        btnAtivar = view.findViewById(R.id.btnAtivar)
    }

    private fun iniActions() {
        btnAtivar.setOnClickListener {
            val resultado = if (cbAndroid.isChecked) {
                "Eu Sei Android"
            } else {
                "Eu nao sei Android"
            }

            Toast.makeText(
                requireContext(),
                resultado,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}