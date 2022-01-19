package com.learn.todoapp.domain.repositories

import com.learn.todoapp.domain.models.ToDo

interface TodoDbOperationsRepository {

    fun insertTodo(todo: ToDo)
    fun updateTodo(todo: ToDo)
    fun fetchAllTodos(email: String): List<ToDo>

}