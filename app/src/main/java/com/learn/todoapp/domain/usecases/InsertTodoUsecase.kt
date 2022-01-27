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
                getLastUpdatedTodo(todo) // fetching the toDoItem which have ID
            )
        } ?: kotlin.run {
            // is user logged in?
            return 0L
        }
    }

    private suspend fun getLastUpdatedTodo(todo: ToDo) =
        if (todo.id > 0) // In update mode, Id will be there
            todo
        else
            todoDbOperationsRepository.fetchLastTodo()
}