package com.nm.as_010_gson

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context

    private lateinit var btnCJ: Button
    private lateinit var btnLJ: Button

    //
    private lateinit var btnCG: Button
    private lateinit var btnLG: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        btnCJ = findViewById(R.id.btnCJSON)
        btnLJ = findViewById(R.id.btnLJSON)
        btnCG = findViewById(R.id.btnCGSON)
        btnLG = findViewById(R.id.btnLGSON)
    }

    private fun iniActions() {
        btnCJ.setOnClickListener {
            val transmissao = JSONObject()
            transmissao.put("contatos", JSONArray(criarContatos(10)))

            Log.d("JSON", transmissao.toString())
        }

        btnLJ.setOnClickListener {
            val servidor = StringBuilder()
            servidor.append("{\"contatos\":[{\"idcontato\":1,\"nome\":\"Nome - 1\",\"idade\":2},{\"idcontato\":2,\"nome\":\"Nome - 2\",\"idade\":4},{\"idcontato\":3,\"nome\":\"Nome - 3\",\"idade\":6},{\"idcontato\":4,\"nome\":\"Nome - 4\",\"idade\":8},{\"idcontato\":5,\"nome\":\"Nome - 5\",\"idade\":10},{\"idcontato\":6,\"nome\":\"Nome - 6\",\"idade\":12},{\"idcontato\":7,\"nome\":\"Nome - 7\",\"idade\":14},{\"idcontato\":8,\"nome\":\"Nome - 8\",\"idade\":16},{\"idcontato\":9,\"nome\":\"Nome - 9\",\"idade\":18},{\"idcontato\":10,\"nome\":\"Nome - 10\",\"idade\":20}]}")

            val recebimento = JSONObject(servidor.toString())
            val contatosJO = recebimento.getJSONArray("contatos")


            val contatos = ArrayList<Contato> ()
            for (i in 0 until contatosJO.length()) {
                val cAux = Contato(
                    (contatosJO[i] as JSONObject).getLong("idcontato"),
                    (contatosJO[i] as JSONObject).getString("nome"),
                    (contatosJO[i] as JSONObject).getInt("idade")
                )

                contatos.add(cAux)
            }

            val item = 10
        }

        btnCG.setOnClickListener {
            val gson = Gson()
            val receitaDeBolo = ReceitaDeBolo(criarContatosC(5))

            Log.d("GSON", gson.toJson(receitaDeBolo))
        }

        btnLG.setOnClickListener {
            val gson = Gson()
            val servidor = StringBuilder()
            servidor.append("{\"contatos\":[{\"idcontato\":1,\"nome\":\"Nome - 1\",\"idade\":2},{\"idcontato\":2,\"nome\":\"Nome - 2\",\"idade\":4},{\"idcontato\":3,\"nome\":\"Nome - 3\",\"idade\":6},{\"idcontato\":4,\"nome\":\"Nome - 4\",\"idade\":8},{\"idcontato\":5,\"nome\":\"Nome - 5\",\"idade\":10},{\"idcontato\":6,\"nome\":\"Nome - 6\",\"idade\":12},{\"idcontato\":7,\"nome\":\"Nome - 7\",\"idade\":14},{\"idcontato\":8,\"nome\":\"Nome - 8\",\"idade\":16},{\"idcontato\":9,\"nome\":\"Nome - 9\",\"idade\":18},{\"idcontato\":10,\"nome\":\"Nome - 10\",\"idade\":20}]}")

            val receitaDeBolo = gson.fromJson(servidor.toString(), ReceitaDeBolo::class.java)

            val i = 10
        }

    }

    private fun criarContatos(quantidade: Int): ArrayList<JSONObject> {
        val contatos = ArrayList<JSONObject>()

        for (i in 1..quantidade) {
            val cAux = Contato()
            cAux.idcontato = i.toLong()
            cAux.nome = "Nome - $i"
            cAux.idade = i * 2

            contatos.add(cAux.toJSONObject())
        }

        return contatos
    }

    private fun criarContatosC(quantidade: Int): ArrayList<Contato> {
        val contatos = ArrayList<Contato>()

        for (i in 1..quantidade) {
            val cAux = Contato()
            cAux.idcontato = i.toLong()
            cAux.nome = "Nome - $i"
            cAux.idade = i * 2

            contatos.add(cAux)
        }

        return contatos
    }
}