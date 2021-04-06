package com.nm.as_008_dbase.model

data class Contato(
    var idcontato: Long = -1L,
    var nome: String = "",
    var telefone: String = "",
    var idade: Int = -1
) {
    override fun toString(): String {
        return nome
    }
}
