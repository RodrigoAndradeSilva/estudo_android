package com.nm.as_012_mvp.dao

import com.nm.as_012_mvp.model.User

open class UserDao {

    // Mock -> Objeto falso
    open fun getUser(name: String, password: String): User? {
        return if (name.equals("Hugo", ignoreCase = true) && password == "T123") {
            User(10L, "Hugo", "T123")
        } else {
            null
        }
    }
}