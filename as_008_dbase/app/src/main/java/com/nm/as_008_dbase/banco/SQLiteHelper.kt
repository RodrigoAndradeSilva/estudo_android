package com.nm.as_008_dbase.banco

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception
import java.lang.StringBuilder

class SQLiteHelper(
    context: Context,
    banco: String?,
    factory: SQLiteDatabase.CursorFactory?,
    versao: Int
) : SQLiteOpenHelper(context, banco, factory, versao) {

    // verificar se o banco existe fisicamente na maquina. Não exister roda onCreate
    override fun onCreate(db: SQLiteDatabase?) {
        // Scripts de criacao das tabelas
        try {
            val sb = StringBuilder()
            sb
                .append(
                    "CREATE TABLE IF NOT EXISTS [contatos](\n" +
                            "          [idcontato] INT PRIMARY KEY NOT NULL,\n" +
                            "          [nome] TEXT NOT NULL,\n" +
                            "          [telefone] TEXT NOT NULL,\n" +
                            "          [idade] INT NOT NULL\n" +
                            "        );"
                )

            val comandos = sb.toString().split(";")

            for (i in comandos.indices) {
                db?.execSQL(comandos[i].toLowerCase())
            }

        } catch (e: Exception) {
            // tratativas de erro
        }
    }

    // Só ira executar quando a base de dados existir, porem a versao do banco for diferente
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // roda aqui dentro
        // Scripts de criacao das tabelas
        try {
            val sb = StringBuilder()
            sb
                .append(
                    "DROP TABLE IF EXISTS [contatos];"
                )

            val comandos = sb.toString().split(";")

            for (i in comandos.indices) {
                db?.execSQL(comandos[i].toLowerCase())
            }

        } catch (e: Exception) {
            // tratativas de erro
        }

        onCreate(db)
    }

}