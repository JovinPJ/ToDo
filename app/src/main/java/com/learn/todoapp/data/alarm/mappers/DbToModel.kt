package com.learn.todoapp.data.alarm.mappers

import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.data.db.tables.TodoTables

fun TodoTables.toModel(): AlarmToDo {
    return AlarmToDo(
        id = id,
        title = title,
        description = description,
        hour = hour,
        minute = minute,
        date = date,
        toDoType = type,
        alarmTime = alarmTime ?: 0L
    )
}