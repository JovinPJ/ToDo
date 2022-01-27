package com.learn.todoapp.domain.repositories

import com.learn.todoapp.domain.models.ToDo

interface AlarmRepository {

    suspend fun registerAlarm(todo: ToDo): Long
    suspend fun updateAlarm(todo: ToDo): Long
    suspend fun cancelAlarm(todo: ToDo)

}