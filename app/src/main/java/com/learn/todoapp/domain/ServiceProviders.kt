package com.learn.todoapp.domain

import com.learn.todoapp.domain.repositories.LoginRepository
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository
import com.learn.todoapp.domain.usecases.*


fun provideLoginUseCase(
    loginRepository: LoginRepository,
    preferenceRepository: PreferenceRepository
): LoginUsecase = LoginUsecase(loginRepository, preferenceRepository)

fun provideFetchAllTodosUseCase(
    todoDbOperationsRepository: TodoDbOperationsRepository,
    preferenceRepository: PreferenceRepository
): FetchAllTodosUsecase = FetchAllTodosUsecase(todoDbOperationsRepository, preferenceRepository)

fun provideUserTokenUseCase(
    preferenceRepository: PreferenceRepository
): UserTokenUsecase = UserTokenUsecase(preferenceRepository)

fun provideInsertTodoUseCase(
    todoDbOperationsRepository: TodoDbOperationsRepository,
    preferenceRepository: PreferenceRepository
): InsertTodoUsecase = InsertTodoUsecase(
    todoDbOperationsRepository,
    preferenceRepository
)

fun provideDeleteTodoUseCase(
    todoDbOperationsRepository: TodoDbOperationsRepository,
    preferenceRepository: PreferenceRepository
): DeleteTodoUsecase = DeleteTodoUsecase(
    todoDbOperationsRepository,
    preferenceRepository
)