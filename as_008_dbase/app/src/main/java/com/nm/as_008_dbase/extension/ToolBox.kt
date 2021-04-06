package com.nm.as_008_dbase.extension

import java.lang.Exception

fun String.SafeInt() : Int {
    return try {
        this.toInt()
    } catch (e: Exception) {
        -1
    }
}