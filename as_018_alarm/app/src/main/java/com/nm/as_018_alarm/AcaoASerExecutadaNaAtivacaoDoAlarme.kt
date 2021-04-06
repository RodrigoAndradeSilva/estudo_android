package com.nm.as_018_alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

// Representa o que será feito quando o alarme for disparado
class AcaoASerExecutadaNaAtivacaoDoAlarme : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        // Sincrona ou Assincrona? -> SINCRONO -> TAREFAS DEMORADAS VAO TRAVAR O APP
        // context.startService()
        Log.d("ABACAXI", "Passei pela Acao do Alarme!!!")
    }

}