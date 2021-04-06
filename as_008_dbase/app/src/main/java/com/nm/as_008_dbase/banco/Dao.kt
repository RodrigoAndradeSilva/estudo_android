package com.nm.as_008_dbase.banco

import android.content.Context
import android.database.sqlite.SQLiteDatabase

open class Dao(
    private val context: Context
) {

    protected var db: SQLiteDatabase? = null

    fun abrirBanco() {
        val dbHelper = SQLiteHelper(
            context, "impacta.db3", null, 1
        )

        this.db = dbHelper.writableDatabase // solicitacao valida de acesso ao banco
    }

    fun fechaBanco() {
        db?.close()
        db = null
    }

}