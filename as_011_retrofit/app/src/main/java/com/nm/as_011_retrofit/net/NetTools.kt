package com.nm.as_011_retrofit.net

import com.google.gson.GsonBuilder
import com.nm.as_011_retrofit.util.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createWebAPI() : WebAPI {
    val client = OkHttpClient.Builder().build()

    val retroAPI = Retrofit.Builder()
        .baseUrl(Constantes.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        )
        .client(client).build()

    return retroAPI.create(WebAPI::class.java)
}