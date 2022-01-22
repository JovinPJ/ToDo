package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class DeleteTodoUsecase(
    private val todoDbOperationsRepository: TodoDbOperationsRepository,
    private val preferenceRepository: PreferenceRepository
) {

    suspend fun deleteTodo(todo: ToDo) {
        preferenceRepository.getUserToken()?.let {
            todoDbOperationsRepository.deleteTodo(it, todo)
        }?: kotlin.run {
            // is user logged in?
        }
    }
}