package com.nm.as_008_dbase.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.nm.as_008_dbase.banco.Dao
import com.nm.as_008_dbase.model.Contato
import java.lang.Exception

class ContadoDao(
    context: Context
) : Dao(context) {

    companion object {
        const val TABELA = "contatos"

        const val IDCONTATO = "idcontato"
        const val NOME = "nome"
        const val TELEFONE = "telefone"
        const val IDADE = "idade"
    }

    fun inserirContato(contato: Contato) {
        abrirBanco()

        val cv = ContentValues()
        cv.put(IDCONTATO, contato.idcontato)
        cv.put(NOME, contato.nome)
        cv.put(TELEFONE, contato.telefone)
        cv.put(IDADE, contato.idade)

        db?.insert(TABELA, null, cv)

        fechaBanco()
    }

    fun atualizarContato(contato: Contato) {
        abrirBanco()

        val cv = ContentValues()

        val filtro = " $IDCONTATO = ? "
        val argumentos = arrayOf(contato.idcontato.toString())

        cv.put(NOME, contato.nome)
        cv.put(TELEFONE, contato.telefone)
        cv.put(IDADE, contato.idade)

        db?.update(TABELA, cv, filtro, argumentos)

        fechaBanco()
    }

    fun apagarContato(idContato: Long) {
        abrirBanco()

        val filtro = " $IDCONTATO = ? "
        val argumentos = arrayOf(idContato.toString())

        db?.delete(TABELA, filtro, argumentos)

        fechaBanco()
    }

    fun obterContatoById(idContato: Long): Contato? {
        var cAux : Contato? = null

        abrirBanco()

        val cursor: Cursor?

        try {
            val comando = " select * from $TABELA where $IDCONTATO = ? "
            val argumentos = arrayOf(idContato.toString())

            cursor = db?.rawQuery(comando, argumentos)

            while (cursor!!.moveToNext()) {
                cAux = Contato()
                cAux.idcontato = cursor.getLong(cursor.getColumnIndex(IDCONTATO))
                cAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
                cAux.telefone = cursor.getString(cursor.getColumnIndex(TELEFONE))
                cAux.idade = cursor.getInt(cursor.getColumnIndex(IDADE))
            }

            cursor.close()

        } catch (e: Exception) {
            // tratativa erro
        }

        fechaBanco()

        return cAux
    }

    fun obterListaDeContatos(): ArrayList<Contato> {
        val contatos = ArrayList<Contato>() // lista vazia

        abrirBanco()

        val cursor: Cursor?

        try {
            val comando = " select * from $TABELA order by $NOME "
            cursor = db?.rawQuery(comando, null)

            while (cursor!!.moveToNext()) {
                val cAux = Contato() // criando um espaco novo de memoria
                cAux.idcontato = cursor.getLong(cursor.getColumnIndex(IDCONTATO))
                cAux.nome = cursor.getString(cursor.getColumnIndex(NOME))
                cAux.telefone = cursor.getString(cursor.getColumnIndex(TELEFONE))
                cAux.idade = cursor.getInt(cursor.getColumnIndex(IDADE))

                contatos.add(cAux)
            }

            cursor.close()

        } catch (e: Exception) {
            // tratativa erro
        }

        fechaBanco()

        return contatos
    }

    fun proximoId(): Long {
        var proID = 1L

        abrirBanco()

        val cursor: Cursor?

        try {
            val comando = " select ifnull(max($IDCONTATO) + 1 , 1) as id from $TABELA "
            cursor = db?.rawQuery(comando, null)

            while (cursor!!.moveToNext()) {
                proID = cursor.getLong(cursor.getColumnIndex("id"))
            }

            cursor.close()

        } catch (e: Exception) {
            // tratativa erro
        }

        fechaBanco()

        return proID
    }

}