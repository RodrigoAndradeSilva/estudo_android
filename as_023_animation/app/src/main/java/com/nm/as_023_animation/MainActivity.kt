package com.nm.as_023_animation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    private lateinit var context: Context

    private lateinit var btnAlpha: Button
    private lateinit var btnRotate: Button
    private lateinit var btnScale: Button
    private lateinit var btnAll: Button
    private lateinit var btnTrans: Button

    private lateinit var ivAvatar: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.telainicial)

        iniVars()
        iniActions()
    }

    private fun iniVars() {
        context = this
        btnAlpha = findViewById(R.id.btnAlpha)
        btnRotate = findViewById(R.id.btnRotation)
        btnScale = findViewById(R.id.btnScale)
        btnAll = findViewById(R.id.btnAll)
        btnTrans = findViewById(R.id.btnTrans)

        ivAvatar = findViewById(R.id.ivAvatar)
    }

    private fun iniActions() {
        btnAlpha.setOnClickListener(botaoListener)
        btnRotate.setOnClickListener(botaoListener)
        btnScale.setOnClickListener(botaoListener)
        btnAll.setOnClickListener(botaoListener)
        btnTrans.setOnClickListener(botaoListener)
    }

    private val botaoListener = View.OnClickListener {
        when (it.id) {
            R.id.btnAlpha -> {
                aplicarEfeito(context, R.anim.alpha, ivAvatar, this)
            }

            R.id.btnRotation -> {
                aplicarEfeito(context, R.anim.rotate, ivAvatar, this)
            }

            R.id.btnScale -> {
                aplicarEfeito(context, R.anim.scale, ivAvatar, this)
            }

            R.id.btnAll -> {
                aplicarEfeito(context, R.anim.all, ivAvatar, this)
            }

            R.id.btnTrans -> {
                aplicarEfeito(context, R.anim.translate, ivAvatar, this)
            }
            else -> {
                // tratamento de erros
            }
        }
    }


    //region Animation
    fun btnStatus(status: Boolean) {
        btnAlpha.isEnabled = status
        btnRotate.isEnabled = status
        btnScale.isEnabled = status
        btnAll.isEnabled = status
        btnTrans.isEnabled = status
    }

    override fun onAnimationStart(animation: Animation?) {
        btnStatus(false)
    }

    override fun onAnimationEnd(animation: Animation?) {
        btnStatus(true)
    }

    override fun onAnimationRepeat(animation: Animation?) {
        // Not Implemented
    }
    //endregion
}