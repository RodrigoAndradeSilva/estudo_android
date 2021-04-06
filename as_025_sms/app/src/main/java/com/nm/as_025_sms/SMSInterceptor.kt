package com.nm.as_025_sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage

class SMSInterceptor : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.let {

            val bundle = it.extras

            // acumulador de partes da mensagem
            var msgs: Array<SmsMessage>? = null

            val mensagemCompleta = StringBuilder()

            if (bundle != null) {
                val pdus = bundle.get("pdus") as Array<Any>
                msgs = arrayOfNulls<SmsMessage>(pdus.size) as Array<SmsMessage>

                for (i in msgs.indices) {
                    msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, "UTF-8")
                    mensagemCompleta.append(msgs[i].messageBody)
                }

                analisarMensagem(context, mensagemCompleta.toString())
            }

        }

    }

    private fun analisarMensagem(context: Context?, mensagem: String) {
        // Logica
        abortBroadcast // mensagem morreia aqui
    }


}