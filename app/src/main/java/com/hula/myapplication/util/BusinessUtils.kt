package com.hula.myapplication.util

object BusinessUtils {

    @JvmStatic
    fun getFirst(content: String?): String {
        return (content ?: "").split(",")[0]
    }

    @JvmStatic
    fun getStringTimeHHmm(timeMs: Long): String {
        var _timeMs = timeMs
        _timeMs = Math.abs(_timeMs)
        val totalSeconds = (_timeMs + 500) / 1000
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600
        var pmOrAm = "AM"
        if (hours >= 12) {
            pmOrAm = "PM"
        }
        return String.format(
            "%02d:%02d %s",
            hours,
            minutes,
            pmOrAm
        )
    }
}
