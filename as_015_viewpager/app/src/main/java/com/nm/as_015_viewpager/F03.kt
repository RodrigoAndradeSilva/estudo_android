package com.nm.as_015_viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class F03 : Fragment() {

    private lateinit var tvStatus: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f03, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iniVars(view)
        iniActions()
    }

    private fun iniVars(view: View) {
        tvStatus = view.findViewById(R.id.tvStatus)
    }

    private fun iniActions() {
        // Not Implemented
    }

    // Setter
    fun mudarStatus(status: String) {
        tvStatus.text = status
    }

}