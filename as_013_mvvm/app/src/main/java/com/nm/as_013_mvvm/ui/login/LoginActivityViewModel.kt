package com.nm.as_013_mvvm.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nm.as_013_mvvm.R
import com.nm.as_013_mvvm.dao.UserDao
import com.nm.as_013_mvvm.util.extensions.Db

class LoginActivityViewModel() : ViewModel() {

    var userDao = UserDao()

    // Observados
    var resposta = MutableLiveData<Int>()
    var navegacao = MutableLiveData<Boolean>()

    fun processLogin(name: String, password: String) {
        if (name.Db().isEmpty()) {
            resposta.value = R.string.error_no_name

            return
        }

        if (password.Db().isEmpty()) {
            resposta.value = R.string.error_no_password

            return
        }

        if (userDao.getUser(name.Db(), password.Db()) == null) {
            resposta.value = R.string.error_invalid_credentials
        } else {
            navegacao.value = true
        }
    }
}