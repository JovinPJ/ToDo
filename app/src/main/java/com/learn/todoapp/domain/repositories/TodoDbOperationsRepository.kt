package com.learn.todoapp.domain.repositories

import com.learn.todoapp.domain.models.ToDo

interface TodoDbOperationsRepository {

    suspend fun insertOrUpdateTodo(userToken: String, todo: ToDo)
    suspend fun updateTodo(userToken: String, todo: ToDo)
    suspend fun fetchAllTodos(userToken: String): List<ToDo>
    suspend fun fetchTodo(userToken: String, id: Int): ToDo
    suspend fun deleteTodo(userToken: String, todo: ToDo)

}