package com.nm.as_003_checkbox_radiobutton_togglebutton

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context

    private lateinit var cbAndroid: CheckBox
    private lateinit var cbIos: CheckBox
    private lateinit var rg: RadioGroup
    private lateinit var rbM: RadioButton
    private lateinit var rbF: RadioButton
    private lateinit var tbTomada: ToggleButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this

        cbAndroid = findViewById(R.id.cbAndroid)
        cbIos = findViewById(R.id.cbIos)
        rg = findViewById(R.id.rg)
        rbM = findViewById(R.id.rbM)
        rbF = findViewById(R.id.rbF)
        tbTomada = findViewById(R.id.tbTomada)

        serverToScreen()
        screenToServer()
    }

    private fun serverToScreen() { // conversao -> conversores (presentation)
        var android = 1
        var ios = 0

        var sexo = "M"

        var tomada = true

        // escolhar multiplas
        cbAndroid.isChecked = android == 1
        cbIos.isChecked = ios == 1

        when (sexo) {
            "M" -> {
                rbM.isChecked = true
            }
            "F" -> {
                rbF.isChecked = true
            }
            else -> {
                // opcao indisponivel gerar log suporte
            }
        }

        tbTomada.isChecked = tomada // quase não existe
    }

    private fun screenToServer() { // conversao
        var android = -1
        var ios = -1

        var sexo = ""

        var tomada = false

        android = if (cbAndroid.isChecked) {
            1
        } else {
            0
        }

        ios = if (cbIos.isChecked) {
            1
        } else {
            0
        }

        when(rg.checkedRadioButtonId) {
            R.id.rbM -> {
                sexo = "M"
            }
            R.id.rbF -> {
                sexo = "F"
            }
            else -> {
                // tratativa erro else
            }
        }

        tomada = tbTomada.isChecked

    }

    private fun iniActions() {
        cbIos.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "iOs Marcado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "iOs Desmarcado", Toast.LENGTH_SHORT).show()
            }
        }

        rg.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbM -> {
                    Toast.makeText(this, "Masculino", Toast.LENGTH_SHORT).show()
                }
                R.id.rbF -> {
                    Toast.makeText(this, "Feminino", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // tratativa de erro
                }
            }
        }
    }

    private fun mudarStatus() {
        val faces = arrayListOf(CheckBox(this),CheckBox(this), CheckBox(this))
        faces[0].isChecked
    }
}