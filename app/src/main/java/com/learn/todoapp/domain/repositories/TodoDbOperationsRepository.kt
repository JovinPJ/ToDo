package com.learn.todoapp.domain.repositories

import com.learn.todoapp.domain.models.ToDo

interface TodoDbOperationsRepository {

    suspend fun insertTodo(userToken: String?, todo: ToDo)
    suspend fun updateTodo(todo: ToDo)
    suspend fun fetchAllTodos(email: String): List<ToDo>

}