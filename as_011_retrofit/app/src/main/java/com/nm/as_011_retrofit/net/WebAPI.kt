package com.nm.as_011_retrofit.net

import com.nm.as_011_retrofit.model.PacienteResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WebAPI {

    @FormUrlEncoded
    @POST("testecargapaciente2.php")
    fun obterPacientes(@Field("json") json: String) : Call<PacienteResponse> // assinatura do metodo

}