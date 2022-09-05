package com.hula.myapplication.util

object BusinessUtils {

    @JvmStatic
    fun getFirst(content: String?): String {
        return (content ?: "").split(",")[0]
    }
}
