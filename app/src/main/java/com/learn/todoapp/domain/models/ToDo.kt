package com.learn.todoapp.domain.models

data class ToDo(
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val toDoType: ToDoType
)
