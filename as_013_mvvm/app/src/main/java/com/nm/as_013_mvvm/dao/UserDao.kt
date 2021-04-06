package com.nm.as_013_mvvm.dao

import com.nm.as_013_mvvm.model.User

open class UserDao : UserDaoI{
    // Mock -> Objeto falso
    override fun getUser(name: String, password: String): User? {
        return if (name.equals("Hugo", ignoreCase = true) && password == "T123") {
            User(10L, "Hugo", "T123")
        } else {
            null
        }
    }
}