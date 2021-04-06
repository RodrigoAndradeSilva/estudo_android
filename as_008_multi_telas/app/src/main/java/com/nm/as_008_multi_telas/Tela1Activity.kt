package com.nm.as_008_multi_telas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Tela1Activity : BaseActivity() {

    private lateinit var context: Context
    private lateinit var btnCS: Button
    private lateinit var btnCR: Button

    override fun getLayout(): Int {
        return R.layout.tela1
    }

    override fun iniVars() {
        context = this

        btnCS = findViewById(R.id.btnCS)
        btnCR = findViewById(R.id.btnCR)
    }

    override fun iniActions() {
        btnCS.setOnClickListener {
            callTela2CS(Constantes.TIPO_1, 100)
        }

        btnCR.setOnClickListener {
            callTela2CR(Constantes.TIPO_2, 500)
        }
    }

    private fun callTela2CS(tipo: Int, valor: Int) {
        startActivity(
            Tela2Activity.newInstance(
                context,
                tipo,
                valor
            )
        )

        finish()
    }

    private fun callTela2CR(tipo: Int, valor: Int) {
        startActivityForResult(
            Tela2Activity.newInstance(
                context,
                tipo,
                valor
            ), PROCESSO_M100
        )
    }

    // processar o retorno esperado da tela ativada pelo "startActivityForResult"
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            PROCESSO_M100 -> {
                processarRetornoM100(resultCode, data)
            }
            else -> {
                // tratativa erros
            }
        }
    }

    private fun processarRetornoM100(resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            val resultado = data?.getIntExtra(Tela2Activity.RESULTADO, -1)
            Toast.makeText(
                context,
                resultado.toString(),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "CANCELADO",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val PROCESSO_M100 = 10

        fun newInstance(context: Context): Intent {
            return Intent(context, Tela1Activity::class.java)
        }
    }

}