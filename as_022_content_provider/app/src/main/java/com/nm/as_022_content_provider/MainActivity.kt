package com.nm.as_022_content_provider

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import android.widget.SimpleAdapter
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var lvContatos: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        lvContatos = findViewById(R.id.lvContatos)

        contactsPermissionHandling()
    }

    private fun iniActions() {
        lvContatos.setOnItemClickListener { parent, view, position, id ->
            verificarExibirTelefone(
                parent.getItemAtPosition(position) as HMAux
            )
        }
    }

    private fun contactsPermissionHandling() {
        if (hasContactsPermission(context)) {
            montarListaContatos()
        } else {
            requestContactsPermission(this)
        }
    }

    private fun montarListaContatos() {
        // ContentProvider => WebService API
        val uriContatos = ContactsContract.Contacts.CONTENT_URI
        val order = ContactsContract.Contacts.DISPLAY_NAME

        val id = ContactsContract.Contacts._ID // Primary Key
        val nome = ContactsContract.Contacts.DISPLAY_NAME
        val temTelefone = ContactsContract.Contacts.HAS_PHONE_NUMBER

        //bucar a informacao no contentProvider
        val cursor = contentResolver.query(
            uriContatos,
            null, // campos que estao vindo
            null, // filtos
            null, // argumentos do meu filtro
            order
        )

        val contatos = arrayListOf<HMAux>() // lista vazia

        // mover os dados do cursor para a lista
        while (cursor!!.moveToNext()) {
            val item = HMAux() // chaves 0
            item[HMAux.ID] = cursor.getString(cursor.getColumnIndex(id))
            item[HMAux.NOME] = cursor.getString(cursor.getColumnIndex(nome))
            item[HMAux.TELEFONE] = cursor.getString(cursor.getColumnIndex(temTelefone))

            contatos.add(item)
        }

        cursor.close()

        // montar o adaptador
        val De = arrayOf(HMAux.NOME)
        val Para = intArrayOf(android.R.id.text1)
        lvContatos.adapter = SimpleAdapter(
            context,
            contatos,
            android.R.layout.simple_list_item_1,
            De,
            Para
        )
    }

    private fun verificarExibirTelefone(hmAux: HMAux) {
        if (hmAux[HMAux.TELEFONE] == "1") {
            val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

            val id = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
            val nome = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            val numero = ContactsContract.CommonDataKinds.Phone.NUMBER

            var cursor = contentResolver.query(
                uriPhone, // api
                arrayOf(id, nome, numero), // campos
                " $id = ? ", // filtro
                arrayOf(hmAux[HMAux.ID]),
                null
            )

            val resultado = StringBuilder() // vazio
            while (cursor!!.moveToNext()) {
                resultado
                    .append("\n")
                    .append(cursor.getString(cursor.getColumnIndex(numero)))
            }

            showMessage(context, resultado.toString())

        } else {
            showMessage(context, "Nao tem Telefone")
        }
    }
}