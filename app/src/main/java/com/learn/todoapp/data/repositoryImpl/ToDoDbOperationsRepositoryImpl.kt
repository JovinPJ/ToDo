package com.learn.todoapp.data.repositoryImpl

import com.learn.todoapp.data.db.dao.TodoDao
import com.learn.todoapp.data.db.mappers.toDB
import com.learn.todoapp.data.db.mappers.toDomain
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class ToDoDbOperationsRepositoryImpl(private val todoDao: TodoDao) : TodoDbOperationsRepository {

    override suspend fun insertTodo(userToken: String, todo: ToDo) {
        todoDao.insertTodo(todo.toDB(userToken))
    }

    override suspend fun updateTodo(todo: ToDo) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchAllTodos(userToken: String): List<ToDo> {
        return todoDao.fetchTodos(userToken).map { it.toDomain() }
    }

    override suspend fun deleteTodo(userToken: String, todo: ToDo) {
        todoDao.deleteTodo(todo.toDB(userToken))
    }
}