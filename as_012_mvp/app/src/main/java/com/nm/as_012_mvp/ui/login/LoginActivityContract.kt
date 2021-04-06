package com.nm.as_012_mvp.ui.login

interface LoginActivityContract {

    // especificar as responsabilidades da tela
    interface I_View {
        fun execShowMessages(resId: Int)
        fun execNav()
    }

    // especificar as responsabilidades do meu presenter
    interface I_Presenter {
        fun processLogin(name: String, password: String)
    }

}