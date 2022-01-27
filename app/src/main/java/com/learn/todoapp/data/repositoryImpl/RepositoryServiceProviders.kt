package com.learn.todoapp.data.repositoryImpl

import com.learn.todoapp.data.api.TodoApi
import com.learn.todoapp.domain.repositories.LoginRepository

fun provideLoginRepository(todoApi: TodoApi): LoginRepository {
    return LoginRepositoryImpl(todoApi)
}

