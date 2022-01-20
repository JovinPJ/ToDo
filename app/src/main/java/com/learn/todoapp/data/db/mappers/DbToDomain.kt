package com.learn.todoapp.data.db.mappers

import com.learn.todoapp.data.db.tables.TodoTables
import com.learn.todoapp.domain.models.ToDo

fun TodoTables.toDomain(): ToDo {
    return ToDo(
        title = title,
        description = description,
        time = time,
        date = date,
        toDoType = type
    )
}