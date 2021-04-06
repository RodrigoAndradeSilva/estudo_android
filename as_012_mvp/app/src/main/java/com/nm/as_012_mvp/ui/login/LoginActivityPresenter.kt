package com.nm.as_012_mvp.ui.login

import com.nm.as_012_mvp.R
import com.nm.as_012_mvp.dao.UserDao
import com.nm.as_012_mvp.util.extensions.Db

class LoginActivityPresenter(
    private var userDao: UserDao, // acesso ao banco de dados
    private var mView: LoginActivityContract.I_View // me forneca qualquer objeto
    // que saiba de comunicar com o usuario.
) : LoginActivityContract.I_Presenter {

    override fun processLogin(name: String, password: String) {
        if (name.Db().isEmpty()) {
            mView.execShowMessages(R.string.error_no_name)
            return
        }

        if (password.Db().isEmpty()) {
            mView.execShowMessages(R.string.error_no_password)
            return
        }

        if (userDao.getUser(name.Db(),password.Db()) == null) {
            mView.execShowMessages(R.string.error_invalid_credentials)
        } else {
            mView.execNav()
        }
    }
}