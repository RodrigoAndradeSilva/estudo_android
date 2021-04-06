package com.nm.as_013_mvvm.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nm.as_013_mvvm.R
import com.nm.as_013_mvvm.ui.menu.MenuActivity
import com.nm.as_013_mvvm.util.mensageria.showMessage

class LoginActivity : AppCompatActivity() {
    private lateinit var context: Context
    private lateinit var viewModel: LoginActivityViewModel

    // Controls / Widget
    private lateinit var etName: EditText
    private lateinit var etPassword: EditText

    private lateinit var btnCancel: Button
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        viewModel = ViewModelProvider(this).get(LoginActivityViewModel::class.java)

        etName = findViewById(R.id.etName)
        etPassword = findViewById(R.id.etPassword)
        btnCancel = findViewById(R.id.btnCancel)
        btnLogin = findViewById(R.id.btnLogin)

        // Inicializacao dos Observadores
        viewModel.resposta.observe(this, Observer { idMensagem ->
            showMessage(context, idMensagem)
        })

        viewModel.navegacao.observe(this, Observer { navegacaoStatus ->
            execNav(navegacaoStatus)

        })
    }

    private fun execNav(status: Boolean) {
        if (status) {
            callMenuActivity()
        }
    }

    private fun callMenuActivity() {
        startActivity(
                MenuActivity.newInstance(context)
        )

        finish()
    }

    private fun iniActions() {
        btnCancel.setOnClickListener {
            clearForm()
        }

        btnLogin.setOnClickListener {
            viewModel.processLogin(
                    etName.text.toString(),
                    etPassword.text.toString()
            )
        }
    }

    private fun clearForm() {
        etName.setText("")
        etPassword.setText("")
        //
        etName.requestFocus()
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}