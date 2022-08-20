package com.hula.myapplication.widget.htoast

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import com.hula.myapplication.R

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defstyle: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defstyle) {


    init {
        scaleType = ScaleType.FIT_XY
        setImageResource(R.mipmap.icon_loading)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val animation = AnimationUtils.loadAnimation(context, R.anim.loading_anim)
        startAnimation(animation)
    }


}