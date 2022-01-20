package com.learn.todoapp.data.repositoryImpl

import com.learn.todoapp.data.db.dao.TodoDao
import com.learn.todoapp.data.db.mappers.toDB
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class ToDoDbOperationsRepositoryImpl(private val todoDao: TodoDao) : TodoDbOperationsRepository {

    override suspend fun insertTodo(userToken: String?, todo: ToDo) {
        todo.toDB(userToken)?.let {
            todoDao.insertTodo(it)
        }
    }

    override suspend fun updateTodo(todo: ToDo) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAllTodos(email: String): List<ToDo> {
        TODO("Not yet implemented")
    }
}