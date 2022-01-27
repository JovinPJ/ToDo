package com.learn.todoapp.data.repositoryImpl

import com.learn.todoapp.data.alarm.AlarmHelper
import com.learn.todoapp.data.alarm.mappers.toModel
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.AlarmRepository

class AlarmRepositoryImpl(private val alarmHelper: AlarmHelper) : AlarmRepository {
    override suspend fun registerAlarm(todo: ToDo): Long {
        return alarmHelper.registerAlarm(todo.toModel())
    }

    override suspend fun updateAlarm(todo: ToDo): Long {
        return alarmHelper.updateAlarm(todo.toModel())
    }

    override suspend fun cancelAlarm(todo: ToDo) {
        alarmHelper.cancelAlarm(todo.toModel())
    }

}