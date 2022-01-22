package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class UpdateTodoUsecase(
    private val todoDbOperationsRepository: TodoDbOperationsRepository,
    private val preferenceRepository: PreferenceRepository
) {

    suspend fun updateTodo(todo: ToDo) {
        preferenceRepository.getUserToken()?.let {
            todoDbOperationsRepository.updateTodo(it, todo)
        } ?: kotlin.run {
            // is user logged in?
        }
    }
}