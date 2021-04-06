package com.nm.as_025_sms

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var btnSendSMS: Button
    private lateinit var sm: SmsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telaincial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        sm = SmsManager.getDefault() // mostrar que é diferente
        btnSendSMS = findViewById(R.id.btnSendSMS)
    }

    private fun iniActions() {

        btnSendSMS.setOnClickListener {
            if (hasSMSPermission(context)) {
                val numero = "99999999"
                val mensagem = "Hello World!!!"

                sm.sendTextMessage(
                    numero,
                    null,
                    mensagem,
                    null,
                    null
                )

            } else {
                requestSMSPermission(this)
            }
        }

    }
}