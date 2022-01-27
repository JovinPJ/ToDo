package com.learn.todoapp.data.alarm.utils

import android.app.AlarmManager
import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.domain.models.ToDoType
import java.util.*
import java.util.concurrent.TimeUnit


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

fun AlarmToDo?.getAlarmTriggerStartDate(): Long {
    val selectedCalendar = Calendar.getInstance()
    this?.date?.let { selectedCalendar.timeInMillis = it }

    // using 'rigtNowCalendar' to use current Time, instead of 'selectedCalendar' Time,
    // we are handling Time Calculations separately
    val rightNowCalendar = Calendar.getInstance()
    rightNowCalendar.set(Calendar.SECOND, 0) // eliminating Second and Milli Second
    rightNowCalendar.set(Calendar.MILLISECOND, 0)

    // making the selected time equal to Current time for comparison
    selectedCalendar.set(Calendar.HOUR_OF_DAY, rightNowCalendar.get(Calendar.HOUR_OF_DAY))
    selectedCalendar.set(Calendar.MINUTE, rightNowCalendar.get(Calendar.MINUTE))
    selectedCalendar.set(Calendar.SECOND, 0) // eliminating Second and Milli Second
    selectedCalendar.set(Calendar.MILLISECOND, 0)

    // setting the future date to Trigger from selected date, if the date is past not setting
    if (rightNowCalendar.before(selectedCalendar))
        rightNowCalendar.set(
            selectedCalendar.get(Calendar.YEAR),
            selectedCalendar.get(Calendar.MONTH),
            selectedCalendar.get(Calendar.DATE)
        )
    else if (this?.toDoType == ToDoType.WEEKLY) {
        val msDiff: Long = rightNowCalendar.timeInMillis - selectedCalendar.timeInMillis
        val daysDiff: Long = TimeUnit.MILLISECONDS.toDays(msDiff)
        val elapsedDaysInWeek = daysDiff % 7 // getting the days need to complete a week
        rightNowCalendar.set(
            Calendar.DATE,
            rightNowCalendar.get(Calendar.DATE) + (7 - elapsedDaysInWeek.toInt())
        )
    }

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
