package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class InsertTodoUsecase(
    private val todoDbOperationsRepository: TodoDbOperationsRepository,
    private val preferenceRepository: PreferenceRepository
) {

    suspend fun insertTodo(todo: ToDo) {
        return todoDbOperationsRepository.insertTodo(
            preferenceRepository.getUserToken(),
            todo
        )
    }
}