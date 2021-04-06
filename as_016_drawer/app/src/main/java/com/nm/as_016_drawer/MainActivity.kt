package com.nm.as_016_drawer

import android.content.Context
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var mDrawerToggle: ActionBarDrawerToggle
    private lateinit var mDrawer: DrawerLayout

    // Fragmentos
    private lateinit var fm: FragmentManager
    private lateinit var fContent: FContent
    private lateinit var fOptions: FOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        fm = supportFragmentManager
        fContent = fm.findFragmentById(R.id.m_content) as FContent
        fOptions = fm.findFragmentById(R.id.m_options) as FOptions

        mDrawer = findViewById(R.id.drawer)
        mDrawerToggle = ActionBarDrawerToggle(
                this,
                mDrawer,
                R.string.drawer_aberto,
                R.string.drawer_fechado
        )

        // Ativaco da area do Toggle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        mDrawer.addDrawerListener(
                mDrawerToggle
        )

        mDrawerToggle.syncState()
    }

    private fun iniActions() {
        fOptions.setOnAtivarListener { nome ->
            fContent.mudarStatus(nome)
            fecharDrawer()
        }
    }

    private fun fecharDrawer() {
        mDrawer.closeDrawer(GravityCompat.START)
    }

    // responsavel por exibir o menu de options
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // processa o item selecionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true // eu processei esse item
        }

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            fecharDrawer()
        } else {
            super.onBackPressed()
        }
    }
}