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

    @JvmStatic
    fun getEnTime(date: Date): String {
        val instance = Calendar.getInstance()
        instance.time = date
        val AMPM = if (instance.get(Calendar.AM_PM) == Calendar.AM) "AM" else "PM"
        return "${getEnMonth(instance.get(Calendar.MONTH))} ${instance.get(Calendar.DAY_OF_MONTH)}," +
                "${getEnWeek(instance.get(Calendar.WEDNESDAY))}," +
                "${instance.get(Calendar.HOUR)}:${String.format("%02d", instance.get(Calendar.MINUTE))} $AMPM"
    }

    @JvmStatic
    fun getEnMonth(mouth: Int): String {
        return when (mouth) {
            1 -> "Jan."
            2 -> "Feb."
            3 -> "Mar."
            4 -> "Apr."
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "Aug."
            9 -> "Sept."
            10 -> "Oct."
            11 -> "Nov."
            12 -> "Dec."
            else -> ""
        }
    }

    @JvmStatic
    fun getEnWeek(week: Int): String {
        return when (week) {
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            7 -> "Sunday"
            else -> ""
        }
    }
}
