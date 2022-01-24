package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.AlarmRepository

class RegisterAlarmUsecase(private val alarmRepository: AlarmRepository) {

    suspend fun registerOrUpdateAlarm(todo: ToDo): Long {
        return alarmRepository.registerAlarm(todo)
    }
}