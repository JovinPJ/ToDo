package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class FetchTodoUsecase(
    private val todoDbOperationsRepository: TodoDbOperationsRepository,
    private val preferenceRepository: PreferenceRepository
) {
    suspend fun fetchAll(): List<ToDo> {
        return preferenceRepository.getUserToken()?.let {
            todoDbOperationsRepository.fetchAllTodos(it)
        } ?: listOf()
    }

    suspend fun fetch(id: Int): ToDo? {
        return preferenceRepository.getUserToken()?.let {
            todoDbOperationsRepository.fetchTodo(it, id)
        }
    }
}