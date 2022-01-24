package com.learn.todoapp.data.alarm.utils

import android.app.AlarmManager
import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.domain.models.ToDoType
import java.util.*


const val MINUTE_MILLIS = 60 * 1_000L
const val HOUR_MILLIS = 60 * MINUTE_MILLIS


fun convertTimeToMillis(h: Int, m: Int): Long {
    return (h * HOUR_MILLIS) + (m * MINUTE_MILLIS)
}

fun AlarmToDo.getAlarmTimeInterval(): Long {
    return when (toDoType) {
        ToDoType.DAILY -> dailyAlarmInterval()
        ToDoType.WEEKLY -> weeklyAlarmInterval()
    }
}

fun ToDoType.getUpdateIntervalMillis(): Long {
    return when (this) {
        ToDoType.DAILY -> AlarmManager.INTERVAL_DAY
        ToDoType.WEEKLY -> 7 * AlarmManager.INTERVAL_DAY // 7 days
    }
}

fun Long?.getAlarmTriggerStartDate(): Long {
    val selectedCalendar = Calendar.getInstance()
    this?.let { selectedCalendar.timeInMillis = it }

    // using 'rigtNowCalendar' to use current Time, instead of 'selectedCalendar' Time,
    // we are handling Time Calculations separately
    val rightNowCalendar = Calendar.getInstance()
    rightNowCalendar.set(
        selectedCalendar.get(Calendar.YEAR),
        selectedCalendar.get(Calendar.MONTH),
        selectedCalendar.get(Calendar.DATE)
    )
    rightNowCalendar.set(Calendar.SECOND, 0) // eliminating Second and Milli Second
    rightNowCalendar.set(Calendar.MILLISECOND, 0)
    return rightNowCalendar.timeInMillis
}

fun getUpdateAlarmTriggerStartTime(h: Int, m: Int): Long {
    val rightNowCalendar = Calendar.getInstance()
    rightNowCalendar.set(Calendar.HOUR_OF_DAY, h)
    rightNowCalendar.set(Calendar.MINUTE, m)
    rightNowCalendar.set(Calendar.SECOND, 0) // eliminating Second and Milli Second
    rightNowCalendar.set(Calendar.MILLISECOND, 0)
    return rightNowCalendar.timeInMillis
}

fun AlarmToDo.dailyAlarmInterval(): Long {
    return if (isTimeOver(hour, minute)) {
        convertTimeToMillis(24, 0).plus(timeDifference(hour, minute)) //schedule for next day
    } else
        timeDifference(hour, minute)
}

fun AlarmToDo.weeklyAlarmInterval(): Long {
    return if (isTimeOver(hour, minute)) {
        convertTimeToMillis(24 * 7, 0).plus(timeDifference(hour, minute)) //schedule for next week
    } else
        timeDifference(hour, minute)
}

fun isTimeOver(h: Int, m: Int): Boolean {
    val timeDiff = timeDifference(h, m)
    return (timeDiff <= 0)
}

fun timeDifference(h: Int, m: Int): Long {
    val rightNow = Calendar.getInstance()
    val currentHour = rightNow.get(Calendar.HOUR_OF_DAY)
    val currentMinute = rightNow.get(Calendar.MINUTE)
    return convertTimeToMillis(h, m).minus(convertTimeToMillis(currentHour, currentMinute))
}
