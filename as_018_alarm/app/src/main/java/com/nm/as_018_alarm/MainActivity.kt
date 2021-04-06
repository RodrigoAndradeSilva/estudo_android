package com.nm.as_018_alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var pi: PendingIntent
    private lateinit var am: AlarmManager

    private lateinit var btnAU: Button
    private lateinit var btnAR: Button
    private lateinit var btnAC: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        am = context.getSystemService(ALARM_SERVICE) as AlarmManager

        btnAU = findViewById(R.id.btnAU)
        btnAR = findViewById(R.id.btnAR)
        btnAC = findViewById(R.id.btnAC)
    }

    private fun iniActions() {
        btnAU.setOnClickListener {

            val intent = Intent(context, AcaoASerExecutadaNaAtivacaoDoAlarme::class.java)
            pi = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    0
            )

            am.set(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + (10 * Consntante.UM_SEGUNDO),
                    pi
            )

        }

        btnAR.setOnClickListener {
            val intent = Intent(context, AcaoASerExecutadaNaAtivacaoDoAlarme::class.java)
            pi = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    0
            )

            am.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    System.currentTimeMillis() + (10 * Consntante.UM_SEGUNDO),
                    (10 * Consntante.UM_SEGUNDO), // 4.3 -> 4.4 NAO PODE SER INFERIOR A 1 MINUTO
                    pi
            )
        }

        btnAC.setOnClickListener {
            val intent = Intent(context, AcaoASerExecutadaNaAtivacaoDoAlarme::class.java)
            pi = PendingIntent.getBroadcast(
                    context,
                    0,
                    intent,
                    0
            )

            am.cancel(pi)
        }

    }
}