package com.learn.todoapp.domain

import com.learn.todoapp.domain.repositories.AlarmRepository
import com.learn.todoapp.domain.repositories.LoginRepository
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository
import com.learn.todoapp.domain.usecases.*


fun provideLoginUseCase(
    loginRepository: LoginRepository,
    preferenceRepository: PreferenceRepository
): LoginUsecase = LoginUsecase(loginRepository, preferenceRepository)

fun provideFetchTodosUseCase(
    todoDbOperationsRepository: TodoDbOperationsRepository,
    preferenceRepository: PreferenceRepository
): FetchTodoUsecase = FetchTodoUsecase(todoDbOperationsRepository, preferenceRepository)

fun provideUserTokenUseCase(
    preferenceRepository: PreferenceRepository
): UserTokenUsecase = UserTokenUsecase(preferenceRepository)

fun provideRegisterAlarmUseCase(
    alarmRepository: AlarmRepository
): AlarmUsecase = AlarmUsecase(alarmRepository)

fun provideInsertTodoUseCase(
    todoDbOperationsRepository: TodoDbOperationsRepository,
    preferenceRepository: PreferenceRepository,
    alarmUsecase: AlarmUsecase
): InsertTodoUsecase = InsertTodoUsecase(
    todoDbOperationsRepository,
    preferenceRepository,
    alarmUsecase
)

fun provideDeleteTodoUseCase(
    todoDbOperationsRepository: TodoDbOperationsRepository,
    preferenceRepository: PreferenceRepository
): DeleteTodoUsecase = DeleteTodoUsecase(
    todoDbOperationsRepository,
    preferenceRepository
)