package com.nm.as_021_shared_preferences

import android.content.Context
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.gson.Gson
import java.lang.Exception


object Constantes {
    const val CONTADOR = "contador"
    const val PRODUTO = "produto"
}

fun setPreferenceContador(context: Context, contador: Int) {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    sharedPreferences.edit().putInt(
        Constantes.CONTADOR,
        contador
    ).apply()
}

fun getPreferenceContador(context: Context): Int {
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    return sharedPreferences.getInt(
        Constantes.CONTADOR,
        -1
    )
}

fun showMessage(context: Context, texto: String) {
    Toast.makeText(
        context,
        texto,
        Toast.LENGTH_SHORT
    ).show()
}

fun setPreferenceProduto(context: Context, produto: Produto) {
    val gson = Gson()
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    sharedPreferences.edit().putString(
        Constantes.PRODUTO,
        gson.toJson(produto)
    ).apply()
}

fun getPreferenceProduto(context: Context): Produto {
    val gson = Gson()
    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    val produtoJSON = sharedPreferences.getString(
        Constantes.PRODUTO,
        ""
    )

    return try {
        gson.fromJson(produtoJSON, Produto::class.java)
    } catch (e: Exception) {
        Produto()
    }
}

