package com.hula.myapplication.widget.dialog

import android.graphics.Color
import android.widget.FrameLayout
import com.blankj.utilcode.util.ScreenUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hula.myapplication.R

open class BaseBottomDialog : BottomSheetDialogFragment() {
    private var peekHeight = -1
    private var behavior: BottomSheetBehavior<FrameLayout>? = null

    // 默认为收缩状态
    private var state: Int? = BottomSheetBehavior.STATE_COLLAPSED
    override fun onStart() {
        super.onStart()
        //设置成全屏
//        if (peekHeight == -1) {
//            peekHeight = (ScreenUtils.getScreenHeight() * 0.4).toInt()
//        }
        val dialog = dialog as? BottomSheetDialog
        val bottomSheet = dialog?.delegate?.findViewById<FrameLayout>(R.id.design_bottom_sheet)
        if (bottomSheet != null) {
            bottomSheet.setBackgroundColor(Color.TRANSPARENT)
            val _behavior = BottomSheetBehavior.from(bottomSheet)
            behavior = _behavior

            if (this.state != null) {
                _behavior.state = state!!
                state = null
            }
            _behavior.skipCollapsed = true
            if (peekHeight != -1) {
                _behavior.peekHeight = peekHeight
                peekHeight = -1
            }
        }
    }

    //竖屏有效
    fun setState(@BottomSheetBehavior.State state: Int) {
        this.state = state
        if (behavior != null) {
            behavior?.state = state
            this.state = null
        }
    }

    //竖屏有效
    fun setPeekHeight(height: Int) {
        this.peekHeight = height
        if (behavior != null) {
            behavior?.peekHeight = peekHeight
            peekHeight = -1
        }
    }
}