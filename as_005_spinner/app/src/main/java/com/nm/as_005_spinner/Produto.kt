package com.nm.as_005_spinner

class Produto(
    var idProduto: Long = -1L,
    var nome: String = "",
    var preco: Double = 0.0
) {

    override fun toString(): String {
        return nome
    }

    fun toHashMap(): HmAux {
        val hmAux = HmAux() // sem chaves

        hmAux[HmAux.IDPRODUTO] = idProduto.toString()
        hmAux[HmAux.NOME] = nome

        return hmAux
    }

    companion object {
        const val IDPRODUTO_C = "idProduto"
        const val NOME_C = "nome"
    }
}