package com.nm.as_008_multi_telas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Tela2Activity : BaseActivity() {

    private lateinit var context: Context

    private lateinit var tvParametros: TextView
    private lateinit var btnM100: Button

    private var tipo: Int = -1
    private var valor: Int = -1

    override fun getLayout(): Int {
        return R.layout.tela2
    }

    override fun iniVars() {
        context = this
        tvParametros = findViewById(R.id.tvParametros)
        btnM100 = findViewById(R.id.btnM100)

        recuperarParametro()
        configurarUI()
    }

    private fun recuperarParametro() {
        tipo = intent.extras?.getInt(TIPO) ?: -1
        valor = intent.extras?.getInt(VALOR) ?: -1
    }

    private fun configurarUI() {
        tvParametros.text = "Parametros: $valor"
        if (tipo == 1) {
            btnM100.isEnabled = false
        }
    }

    override fun iniActions() {
        btnM100.setOnClickListener {
            val resultado = valor * 100

            // funcionar com a caminhao de transporte
            // Vai transportar o que?
            // Resultado da minha conta
            val data = Intent()
            data.putExtra(RESULTADO, resultado)

            setResult(RESULT_OK, data) // dispara um aviso para a Tela1Activity

            onBackPressed()
        }
    }


    companion object {
        const val TIPO = "tipo"
        const val VALOR = "valor"

        const val RESULTADO = "resultado"

        fun newInstance(context: Context, tipo: Int, valor: Int): Intent {
            return Intent(context, Tela2Activity::class.java).apply {
                putExtras(
                    Bundle().apply {
                        putInt(TIPO, tipo)
                        putInt(VALOR, valor)
                    }
                )
            }
        }

//        fun newInstance(context: Context, tipo: Int, valor: Int): Intent {
//            val bundle = Bundle()
//            bundle.putInt(TIPO, tipo)
//            bundle.putInt(VALOR, valor)
//
//            val intent = Intent(context, Tela2Activity::class.java)
//            intent.putExtras(bundle)
//
//            return intent
//        }
    }

    override fun onBackPressed() {
        callTela1()
    }

    private fun callTela1() {
        if (tipo == 1) {
            startActivity(Tela1Activity.newInstance(context))
            finish()
        } else {
            finish()
        }
    }

}