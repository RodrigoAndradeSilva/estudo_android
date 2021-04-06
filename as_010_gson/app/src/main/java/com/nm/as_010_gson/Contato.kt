package com.nm.as_010_gson

import org.json.JSONObject

class Contato(
    var idcontato: Long = -1L,
    var nome: String = "",
    var idade: Int = -1
) {

    override fun toString(): String {
        return nome
    }

    fun toJSONObject(): JSONObject {
        val jsonObject = JSONObject()

        jsonObject.put("idcontato", idcontato)
        jsonObject.put("nome", nome)
        jsonObject.put("idade", idade)

        return jsonObject
    }

}