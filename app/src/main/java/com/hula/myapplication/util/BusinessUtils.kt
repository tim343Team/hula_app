package com.hula.myapplication.util

import java.util.*

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
        var hours = totalSeconds / 3600
        var pmOrAm = "AM"
        if (hours >= 12) {
            pmOrAm = "PM"
            hours -= 12
        }
        return String.format(
            "%02d:%02d %s",
            hours,
            minutes,
            pmOrAm
        )
    }

    @JvmStatic
    fun getHHmmTime(date: Date): Long {
        val instance = Calendar.getInstance()
        instance.time = date
        val hour = instance.get(Calendar.HOUR_OF_DAY)
        val minute = instance.get(Calendar.MINUTE)
        return hour * 60_000L * 60 + minute * 60_000L
    }
}
