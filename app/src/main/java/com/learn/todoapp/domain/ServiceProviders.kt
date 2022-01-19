package com.learn.todoapp.domain

import com.learn.todoapp.domain.repositories.LoginRepository
import com.learn.todoapp.domain.usecases.FetchAllTodosUsecase
import com.learn.todoapp.domain.usecases.LoginUsecase


fun provideLoginUseCase(repository: LoginRepository): LoginUsecase = LoginUsecase(repository)

fun provideFetchAllTodosUseCase(): FetchAllTodosUsecase = FetchAllTodosUsecase()