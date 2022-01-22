package com.learn.todoapp.data.db.mappers

import com.learn.todoapp.data.db.tables.TodoTables
import com.learn.todoapp.domain.models.ToDo

fun TodoTables.toDomain(): ToDo {
    return ToDo(
        id = id,
        title = title,
        description = description,
        hour = hour,
        minute = minute,
        date = date,
        toDoType = type
    )
}