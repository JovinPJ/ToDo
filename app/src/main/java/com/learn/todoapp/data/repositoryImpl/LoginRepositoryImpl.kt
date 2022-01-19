package com.learn.todoapp.data.repositoryImpl

import com.learn.todoapp.data.api.TodoApi
import com.learn.todoapp.data.api.models.User
import com.learn.todoapp.domain.repositories.LoginRepository

class LoginRepositoryImpl(private val todoApi: TodoApi) : LoginRepository {
    override suspend fun login(email: String, password: String) {
        todoApi.login(User(email, password))
    }
}