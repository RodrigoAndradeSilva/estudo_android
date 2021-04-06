package com.nm.as_008_dbase.ui.lista

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import com.nm.as_008_dbase.R
import com.nm.as_008_dbase.dao.ContadoDao
import com.nm.as_008_dbase.model.Contato
import com.nm.as_008_dbase.ui.formulario.FormularioActivity

class ListaActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var contadoDao: ContadoDao

    private lateinit var lvContatos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        contadoDao = ContadoDao(context) // a activity passa a ter acesso ao banco de dados local
        lvContatos = findViewById(R.id.lvContatos)

        montarLista()
    }

    private fun montarLista() {
        lvContatos.adapter = ArrayAdapter<Contato>(
            context,
            android.R.layout.simple_list_item_1,
            contadoDao.obterListaDeContatos()
        )
    }

    private fun iniActions() {
        lvContatos.setOnItemClickListener { parent, view, position, id ->
            val contato = parent.getItemAtPosition(position) as Contato
            chamarFormulario(contato.idcontato)
        }
    }

    // Menu de opcoes -> tres pontinhos verticais -> ouverem opcoes escondidas
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
            R.id.action_nc -> {
                chamarFormulario(-1L)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, ListaActivity::class.java)
        }
    }

    fun chamarFormulario(idContato: Long) {
        startActivity(
            FormularioActivity.newInstance(context, idContato)
        )

        finish()
    }
}