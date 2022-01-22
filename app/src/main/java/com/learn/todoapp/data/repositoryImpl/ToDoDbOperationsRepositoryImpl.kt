package com.learn.todoapp.data.repositoryImpl

import com.learn.todoapp.data.db.dao.TodoDao
import com.learn.todoapp.data.db.mappers.toDB
import com.learn.todoapp.data.db.mappers.toDomain
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

class ToDoDbOperationsRepositoryImpl(private val todoDao: TodoDao) : TodoDbOperationsRepository {

    override suspend fun insertOrUpdateTodo(userToken: String, todo: ToDo) {
        if (todo.id > 0) updateTodo(userToken, todo)
        else todoDao.insertTodo(todo.toDB(userToken))
    }

    override suspend fun updateTodo(userToken: String, todo: ToDo) {
        val todoTable = todo.toDB(userToken)
        todoTable.id = todo.id
        todoDao.updateTodo(todoTable)
    }

    override suspend fun fetchAllTodos(userToken: String): List<ToDo> {
        return todoDao.fetchTodos(userToken).map { it.toDomain() }
    }

    override suspend fun fetchTodo(userToken: String, id: Int): ToDo {
        return todoDao.fetchTodo(userToken, id).toDomain()
    }

    override suspend fun deleteTodo(userToken: String, todo: ToDo) {
        todoDao.deleteTodo(todo.toDB(userToken))
    }
}