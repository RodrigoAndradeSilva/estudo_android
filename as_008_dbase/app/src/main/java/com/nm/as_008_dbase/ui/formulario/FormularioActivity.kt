package com.nm.as_008_dbase.ui.formulario

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nm.as_008_dbase.R
import com.nm.as_008_dbase.dao.ContadoDao
import com.nm.as_008_dbase.extension.SafeInt
import com.nm.as_008_dbase.model.Contato
import com.nm.as_008_dbase.ui.lista.ListaActivity

class FormularioActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var contadoDao: ContadoDao

    private lateinit var etCodigo: EditText
    private lateinit var etNome: EditText
    private lateinit var etTelefone: EditText
    private lateinit var etIdade: EditText

    private lateinit var btnExcluir: Button
    private lateinit var vSpace: View
    private lateinit var btnSalvar: Button

    private var idAtual = -1L

    private var mensagem = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formulario_activity)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        contadoDao = ContadoDao(context)

        etCodigo = findViewById(R.id.etCodigo)
        etNome = findViewById(R.id.etNome)
        etTelefone = findViewById(R.id.etTelefone)
        etIdade = findViewById(R.id.etIdade)

        btnExcluir = findViewById(R.id.btnExcluir)
        vSpace = findViewById(R.id.vSpace)
        btnSalvar = findViewById(R.id.btnSalvar)

        recuperarParametrosEnviados()
        configurarUI()
    }

    private fun configurarUI() {
        if (idAtual != -1L) {
            // recuperar o registro o mover os dados para a tela
            val aux = contadoDao.obterContatoById(idAtual)

            etCodigo.setText(aux?.idcontato.toString())
            etNome.setText(aux?.nome)
            etTelefone.setText(aux?.telefone)
            etIdade.setText(aux?.idade.toString())
            //
            btnExcluir.visibility = View.VISIBLE
            vSpace.visibility = View.VISIBLE
        } else {
            // estamos diante de um caso de novo contato
            btnExcluir.visibility = View.GONE
            vSpace.visibility = View.GONE
        }
    }

    private fun recuperarParametrosEnviados() {
        idAtual = intent.extras?.getLong(IDCONTATO)!!
    }

    private fun iniActions() {
        btnExcluir.setOnClickListener {
            contadoDao.apagarContato(idAtual)
            chamarLista()
        }

        btnSalvar.setOnClickListener {
            if (validarFormulario()) {
                salvar()
            } else {
                showMessage(mensagem)
            }
        }
    }

    private fun validarFormulario(): Boolean {
        val nome = etNome.text.toString().trim()
        val telefone = etTelefone.text.toString().trim()
        val idade = etIdade.text.toString().SafeInt()

        if(nome.isEmpty()) {
            mensagem = "Erro. Nome é Obrigatório!!!"
            return false
        }

        if(telefone.isEmpty()) {
            mensagem = "Erro. Telefone é Obrigatório!!!"
            return false
        }

        if(idade < 5) {
            mensagem = "Erro. Idade Invalida!!!"
            return false
        }

        return true
    }

    private fun salvar() {
        val nome = etNome.text.toString().trim()
        val telefone = etTelefone.text.toString().trim()
        val idade = etIdade.text.toString().SafeInt()

        val aux = Contato()

        // nao importa se eu estou incluindo ou alterando. Mover os dados da tela para a variavel
        aux.nome = nome
        aux.telefone = telefone
        aux.idade = idade

        if (idAtual != -1L) { // atualizando
            aux.idcontato = idAtual
            contadoDao.atualizarContato(aux)
        } else { // inserindo
            idAtual = contadoDao.proximoId()
            aux.idcontato = idAtual
            contadoDao.inserirContato(aux)

            etCodigo.setText(aux.idcontato.toString())
            btnExcluir.visibility = View.VISIBLE
            vSpace.visibility = View.VISIBLE
        }
    }

    private fun showMessage(mensagem: String) {
        Toast.makeText(
            context,
            mensagem,
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        const val IDCONTATO = "idcontato"

        fun newInstance(context: Context, idcontato: Long): Intent {
            return Intent(context, FormularioActivity::class.java).apply {
                putExtras(
                    Bundle().apply {
                        putLong(IDCONTATO, idcontato)
                    }
                )
            }
        }
    }

    fun chamarLista() {
        startActivity(
            ListaActivity.newInstance(context)
        )

        finish()
    }

    override fun onBackPressed() {
        confirmarSaida()
    }

    private fun confirmarSaida() {
        val alerta = AlertDialog.Builder(context)
        alerta.setCancelable(false) // nao deixa voce sair sem selecionar uma opcao da caixa
        alerta.setTitle("Saida do Formulario")
        alerta.setMessage("Tem certeza que voce deseja sair?")
        alerta.setNegativeButton("Nao", null)
        alerta.setPositiveButton("Sim", DialogInterface.OnClickListener { dialog, which ->
            chamarLista()
        })

        alerta.show()
    }
}