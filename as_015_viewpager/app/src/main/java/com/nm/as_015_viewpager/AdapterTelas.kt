package com.nm.as_015_viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class AdapterTelas(
    fragmentManager: FragmentManager,
    val lista: List<Fragment>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(position: Int): Fragment {
        return lista[position]
    }
}