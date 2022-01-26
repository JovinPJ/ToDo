package com.learn.todoapp.domain.models

data class ToDo(
    val id: Int = 0,
    val title: String,
    val description: String?,
    val hour: Int,
    val minute: Int,
    val date: Long?,
    val toDoType: ToDoType,
    var alarmTime: Long = 0
)
