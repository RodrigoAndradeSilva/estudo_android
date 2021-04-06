package com.nm.as_008_multi_telas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())

        iniVars()
        iniActions()
    }

    abstract fun getLayout(): Int
    abstract fun iniVars()
    abstract fun iniActions()

}