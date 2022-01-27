package com.learn.todoapp.data.db.mappers

import com.learn.todoapp.data.db.tables.TodoTables
import com.learn.todoapp.domain.models.ToDo

fun ToDo.toDB(userToken: String): TodoTables {
    return TodoTables(
        title = title,
        userToken = userToken,
        description = description,
        hour = hour,
        minute = minute,
        date = date,
        type = toDoType,
        alarmTime = alarmTime
    )
}