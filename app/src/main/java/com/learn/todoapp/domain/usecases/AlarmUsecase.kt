package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.AlarmRepository

class AlarmUsecase(private val alarmRepository: AlarmRepository) {

    suspend fun registerOrUpdateAlarm(todo: ToDo): Long {
        cancelAlarm(todo)
        return alarmRepository.registerAlarm(todo)
    }

    private suspend fun cancelAlarm(todo: ToDo) {
        alarmRepository.cancelAlarm(todo)
    }
}