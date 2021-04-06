package com.nm.as_013_mvvm.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nm.as_013_mvvm.ui.login.LoginActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        callLoginActivity()
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, MenuActivity::class.java)
        }
    }

    private fun callLoginActivity()  {
        startActivity(
                LoginActivity.newInstance(this)
        )

        finish()
    }
}