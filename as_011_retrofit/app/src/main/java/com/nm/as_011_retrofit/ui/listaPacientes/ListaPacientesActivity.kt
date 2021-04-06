package com.nm.as_011_retrofit.ui.listaPacientes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import com.google.gson.Gson
import com.nm.as_011_retrofit.R
import com.nm.as_011_retrofit.model.Paciente
import com.nm.as_011_retrofit.model.PacienteRequest
import com.nm.as_011_retrofit.model.PacienteResponse
import com.nm.as_011_retrofit.net.createWebAPI
import com.nm.as_011_retrofit.util.HMAux
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaPacientesActivity : AppCompatActivity() {

    private lateinit var context: Context
    private lateinit var lvPaciente: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_pacientes)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        lvPaciente = findViewById(R.id.lvPacientes)

        chamadaWeb()
    }

    private fun chamadaWeb() {
        val gson = Gson()
        val pacienteRequest = PacienteRequest(
            10,
            "Hugo",
            "698dc19d489c4e4db73e28a713eab07b"
        )

        createWebAPI().obterPacientes(
            gson.toJson(pacienteRequest)
        ).enqueue(object : Callback<PacienteResponse> {

            override fun onResponse(
                call: Call<PacienteResponse>,
                response: Response<PacienteResponse>
            ) {
               if (response.isSuccessful) {
                   var resposta:PacienteResponse = response.body()!!
                   val De = arrayOf(HMAux.NOME, HMAux.IDADE) // dados no HMAux
                   val Para = intArrayOf(R.id.tvCellNome, R.id.tvCellIdade) // controles no xml
                   lvPaciente.adapter = SimpleAdapter(
                       context,
                       conversor(resposta.pacientes),
                       R.layout.cell_paciente,
                       De,
                       Para
                   )
               } else {
                   // trataviva de erro
               }
            }

            override fun onFailure(call: Call<PacienteResponse>, t: Throwable) {
                val resposta = t.message

                var i = 10
            }

        })
    }

    private fun conversor(pacientes: List<Paciente>): List<HMAux> {
        val nomes = arrayListOf<HMAux>() // lista vazia

        for(i in pacientes.indices) {
//            val hmAux = HMAux()
//            hmAux[HMAux.ID] = pacientes[i].codigo.toString()
//            hmAux[HMAux.NOME] = pacientes[i].nome
//            hmAux[HMAux.IDADE] = pacientes[i].idade.toString()

            nomes.add(pacientes[i].toHMAux())
        }

        return nomes
    }

    private fun iniActions() {

    }
}