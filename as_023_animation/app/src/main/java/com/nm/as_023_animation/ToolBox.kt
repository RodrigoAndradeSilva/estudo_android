package com.nm.as_023_animation

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

fun aplicarEfeito(context: Context, aniId: Int, view: View, listener: Animation.AnimationListener) {
    val mAnin = AnimationUtils.loadAnimation(
        context,
        aniId
    )

    mAnin.setAnimationListener(listener)
    view.requestLayout()
    view.animation = mAnin
    mAnin.start()
}