package com.nm.as_005_spinner

class HmAux : HashMap<String, String>() {

    companion object {
        const val IDPRODUTO = "idProduto"
        const val NOME = "nome"
    }

    override fun toString(): String {
        return this[NOME] ?: "Erro"
    }
}