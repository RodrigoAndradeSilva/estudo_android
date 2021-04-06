package com.nm.as_017_notificacao

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var nm: NotificationManager

    private lateinit var btnFN: Button
    private lateinit var btnCN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        // interagir com o sistema de notificacao do Android porem o notificationManeger não é a notificacao
        nm = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        btnFN = findViewById(R.id.btnFN)
        btnCN = findViewById(R.id.btnCN)
    }

    private fun iniActions() {
        btnFN.setOnClickListener {
            createNotification()
        }

        btnCN.setOnClickListener {
            cancelNotification()
        }
    }

    private fun createNotification() {
        createNotificationChannel()

        val intent = Intent(context, MainActivity::class.java)

        val pi = PendingIntent.getActivity(
                context,
                0,
                intent,
                0
        )

        val notificacao = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(context, Constantes.CHANNEL_ID)
        } else {
            NotificationCompat.Builder(context)
        }

        notificacao.setSmallIcon(R.mipmap.ic_launcher)
        notificacao.setContentTitle("Sincronismo")
        notificacao.setContentText("Sincronismo em Andamento")
        notificacao.setAutoCancel(true)
        notificacao.setContentIntent(pi)
        notificacao.setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)
        notificacao.priority = Constantes.NOTIFICATION_PRIORITY_HIGH

        nm.notify(Constantes.NOTIFICATION_ID, notificacao.build())
    }

    private fun cancelNotification() {
        nm.cancel(Constantes.NOTIFICATION_ID)
    }

    private fun createNotificationChannel() {
        // Build.VERSION.SDK_INT -> Qual a versao do Android Rodando no Aparelho
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // Criar o Notification Channel
            val mChannel = NotificationChannel(
                    Constantes.CHANNEL_ID,
                    Constantes.NOTIFICATION_NAME,
                    Constantes.NOTIFICATION_PRIORITY_HIGH
            )
            mChannel.description = Constantes.NOTIFICATION_DESC

            nm.createNotificationChannel(mChannel)
        }
    }
}