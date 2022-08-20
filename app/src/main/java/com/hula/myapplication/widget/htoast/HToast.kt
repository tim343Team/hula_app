package com.hula.myapplication.widget.htoast

import android.view.View

interface HToast {
    fun setContentView(contentView: View)

    fun setOutsideTouchable(touchAble:Boolean)

    fun show()

    fun cancel()

    fun setDuration(duration: Int)

}