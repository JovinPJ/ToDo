package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class InsertTodoUsecase(
    private val todoDbOperationsRepository: TodoDbOperationsRepository,
    private val preferenceRepository: PreferenceRepository,
    private val alarmUsecase: AlarmUsecase
) {

    suspend fun insertOrUpdateTodo(todo: ToDo): Long {
        return preferenceRepository.getUserToken()?.let {
            todoDbOperationsRepository.insertOrUpdateTodo(it, todo)
            return alarmUsecase.registerOrUpdateAlarm(
                if (todo.id > 0) // update mode
                    todoDbOperationsRepository.fetchTodo(it, todo.id)
                else
                    todoDbOperationsRepository.fetchLastTodo()
            )
        } ?: kotlin.run {
            // is user logged in?
            return 0L
        }
    }
}