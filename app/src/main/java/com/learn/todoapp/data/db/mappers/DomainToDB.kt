package com.learn.todoapp.data.db.mappers

import com.learn.todoapp.data.db.tables.TodoTables
import com.learn.todoapp.domain.models.ToDo

fun ToDo.toDB(userToken: String?): TodoTables? {
    return userToken?.let {
        TodoTables(
            title = title,
            userToken = it,
            description = description,
            time = time,
            date = date,
            type = toDoType
        )
    }
}