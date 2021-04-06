package com.nm.as_012_mvp.ui.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.nm.as_012_mvp.R
import com.nm.as_012_mvp.dao.UserDao
import com.nm.as_012_mvp.util.mensageria.showMessage

class LoginActivity : AppCompatActivity() , LoginActivityContract.I_View{
    private lateinit var context: Context
    private lateinit var mPresenter: LoginActivityContract.I_Presenter //
    // me forneca alguem que faz a funcao do presentar

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
        etName = findViewById(R.id.etName)
        etPassword = findViewById(R.id.etPassword)
        btnCancel = findViewById(R.id.btnCancel)
        btnLogin = findViewById(R.id.btnLogin)

        mPresenter = LoginActivityPresenter(
            UserDao(),
            this
        )
    }

    private fun iniActions() {
        btnCancel.setOnClickListener {
            clearForm()
        }

        btnLogin.setOnClickListener {
            mPresenter.processLogin(
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

    override fun execShowMessages(resId: Int) {
        showMessage(context, resId)
    }

    override fun execNav() {
        showMessage(context, "Indo Para a Tela de Menu")
    }
}