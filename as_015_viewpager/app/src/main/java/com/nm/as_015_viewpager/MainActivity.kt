package com.nm.as_015_viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var lista: ArrayList<Fragment> // colecao

    private lateinit var tabs: TabLayout
    private lateinit var vp: ViewPager

    private lateinit var f01: F01
    private lateinit var f02: F02
    private lateinit var f03: F03

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this

        tabs = findViewById(R.id.tabs)
        vp = findViewById(R.id.vp)

        montarLista()
    }

    private fun montarLista() {
        lista = arrayListOf() // lista vazia
        f01 = F01()
        f02 = F02()
        f03 = F03()
        lista.add(f01)
        lista.add(f02)
        lista.add(f03)

        vp.adapter = AdapterTelas(
            supportFragmentManager,
            lista
        )

        tabs.setupWithViewPager(vp)

        configTabIcons()
    }

    private fun configTabIcons() {
        val tab01 = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        tab01.text = "Alarme"
        tab01.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.ic_alarm,
            0,
            0
        )
        tabs.getTabAt(0)?.customView = tab01

        val tab02 = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        tab02.text = "Camera"
        tab02.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.ic_camera,
            0,
            0
        )
        tabs.getTabAt(1)?.customView = tab02

        val tab03 = LayoutInflater.from(context).inflate(R.layout.custom_tab, null) as TextView
        tab03.text = "Arvore"
        tab03.setCompoundDrawablesWithIntrinsicBounds(
            0,
            R.drawable.ic_tree,
            0,
            0
        )
        tabs.getTabAt(2)?.customView = tab03
    }

    private fun iniActions() {
        f02.setOnNameSelectedListener { nome ->
            f03.mudarStatus(nome)
            vp.currentItem = 2
        }
    }

    override fun onBackPressed() {
        val ct = vp.adapter!!.count - 1
        if (vp.currentItem in 1..ct) {
            vp.currentItem = vp.currentItem - 1
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}