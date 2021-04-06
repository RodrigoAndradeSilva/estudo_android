package com.nm.as_012_mvp.util.mensageria

import android.content.Context
import android.widget.Toast

fun showMessage(context: Context, message: String) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}
fun showMessage(context: Context, messageResId: Int) {
    Toast.makeText(
        context,
        messageResId,
        Toast.LENGTH_SHORT
    ).show()
}