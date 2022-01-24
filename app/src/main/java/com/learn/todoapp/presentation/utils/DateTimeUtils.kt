package com.learn.todoapp.presentation.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val DATE_DISPLAY_FORMAT = "dd/MM/yyyy"
const val ALARM_TIME_DISPLAY_FORMAT = "EEE, d MMM yyyy, HH:mm"

fun Long.toFormattedDateText(format: String = DATE_DISPLAY_FORMAT): String {
    return try {
        val date = Date(this)
        val simpleDateFormat: DateFormat =
            SimpleDateFormat(format, Locale.getDefault())
        simpleDateFormat.format(date)
    } catch (e: Exception) {
        ""
    }
}

fun displayTime(hour: Int, minute: Int, is24H: Boolean = true): String {
    var amPm = ""
    var tempHour = hour
    if (!is24H) {
        amPm = " ${if (hour < 12) "AM" else "PM"}"
        tempHour = if (hour > 12) hour - 12 else hour

    }
    val hourAsText = if (tempHour < 10) "0$tempHour" else tempHour
    val minuteAsText = if (minute < 10) "0$minute" else minute
    return "$hourAsText:$minuteAsText$amPm"
}


fun displayTimeInHHMm(h: Int, m: Int): String {
    return String.format("%02d:%02d", h, m)
}
