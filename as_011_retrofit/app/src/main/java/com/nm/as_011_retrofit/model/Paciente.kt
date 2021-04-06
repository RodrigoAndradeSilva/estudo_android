package com.nm.as_011_retrofit.model

import com.nm.as_011_retrofit.util.HMAux

data class Paciente(
        var codigo: Long = -1L,
        var idade: Int = -1,
        var nascimento: String = "",
        var nome: String = "",
        var peso: Double = 0.0,
        var sexo: String = "",
        var imagem_local: String = ""
) {
    fun toHMAux() : HMAux {
        return HMAux().apply {
            put(HMAux.ID, codigo.toString())
            put(HMAux.NOME, nome)
            put(HMAux.IDADE, idade.toString())
        }
    }
}