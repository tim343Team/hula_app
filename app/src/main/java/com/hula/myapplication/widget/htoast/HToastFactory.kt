package com.hula.myapplication.widget.htoast

import android.content.Context


object HToastFactory {

    fun factory(context: Context): HToast {
        //context 是 activity 或者是 Application 且 有 悬框权限
//        val userXtoast = (context is Application || context is Activity) && PermissionUtil.canDrawOverlays(context)
        var hToast: HToast? = null
//        if (userXtoast) {
//            hToast = if (context is Activity) {
//                XToastIml(context)
//            } else {
//                XToastIml(context as Application)
//            }
//        }
        if (hToast == null) {
            hToast = SysToastIml(context)
        }
        return hToast
    }
}