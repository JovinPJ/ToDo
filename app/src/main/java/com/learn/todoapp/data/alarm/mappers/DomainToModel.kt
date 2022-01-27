package com.learn.todoapp.data.alarm.mappers

import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.domain.models.ToDo

fun ToDo.toModel(): AlarmToDo {
    return AlarmToDo(
        id = id,
        title = title,
        description = description,
        hour = hour,
        minute = minute,
        date = date,
        toDoType = toDoType,
        alarmTime = alarmTime
    )
}