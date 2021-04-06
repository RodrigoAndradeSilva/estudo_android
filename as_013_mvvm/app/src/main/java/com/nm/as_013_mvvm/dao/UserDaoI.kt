package com.nm.as_013_mvvm.dao

import com.nm.as_013_mvvm.model.User

interface UserDaoI {
    fun getUser(name: String, password: String): User?
}