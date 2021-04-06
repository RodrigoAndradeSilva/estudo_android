package com.nm.as_019_gps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.logging.LogManager

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var lm: LocationManager

    private lateinit var btnGPS: Button
    private lateinit var btnNetWork: Button
    private lateinit var btnStop: Button

    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude: TextView

    private var latitude = 0.0
    private var longitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        lm = context.getSystemService(LOCATION_SERVICE) as LocationManager

        btnGPS = findViewById(R.id.btnGPS)
        btnNetWork = findViewById(R.id.btnNetWork)
        btnStop = findViewById(R.id.btnStop)

        tvLatitude = findViewById(R.id.tvLatitude)
        tvLongitude = findViewById(R.id.tvLongitude)
    }

    @SuppressLint("MissingPermission")
    private fun iniActions() {
        btnGPS.setOnClickListener {
            if (hasGpsPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                btnStatus(false)
                clearForm()

                lm.requestLocationUpdates( // comecei a consumir bateria
                        LocationManager.GPS_PROVIDER,
                        0L,
                        0.0F,
                        listenerLocalizacao
                )
            } else {
                requestGpsPermission(
                        this,
                        arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION
                        )
                )
            }
        }

        btnNetWork.setOnClickListener {
            if (hasGpsPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
                btnStatus(false)
                clearForm()

                lm.requestLocationUpdates( // bem mais economico em termos de bateria.
                        LocationManager.NETWORK_PROVIDER,
                        0L,
                        0.0F,
                        listenerLocalizacao
                )

            } else {
                requestGpsPermission(
                        this,
                        arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION
                        )
                )
            }
        }

        btnStop.setOnClickListener {
            btnStatus(true)
            lm.removeUpdates(listenerLocalizacao) // Operacao GPS nesse momento para de consumir a bateria

            montagemChamadaGoogleMaps()
        }

    }

    private fun montagemChamadaGoogleMaps() {
        val params = "geo:0,0?q=${latitude.toString().replace(",", ".")},${longitude.toString().replace(",", ".")}"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(params))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }

    private fun btnStatus(status: Boolean) {
        btnGPS.isEnabled = status
        btnNetWork.isEnabled = status
    }

    private fun clearForm() {
        tvLatitude.text = "0.0"
        tvLongitude.text = "0.0"
    }

    private val listenerLocalizacao = LocationListener { location ->
        latitude = location.latitude
        longitude = location.longitude

        tvLatitude.text = latitude.toString()
        tvLongitude.text = longitude.toString()

    }
}