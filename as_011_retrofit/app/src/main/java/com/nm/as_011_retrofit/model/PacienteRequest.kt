package com.nm.as_011_retrofit.model

data class PacienteRequest(
        val pacientes: Int,
        val user: String,
        val pwd: String // md5
)