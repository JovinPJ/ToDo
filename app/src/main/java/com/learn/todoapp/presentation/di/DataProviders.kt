package com.learn.todoapp.presentation.di

import com.learn.todoapp.data.api.provideRetrofit
import com.learn.todoapp.data.api.provideTodoApi
import com.learn.todoapp.data.repositoryImpl.provideLoginRepository
import com.learn.todoapp.domain.provideFetchAllTodosUseCase
import com.learn.todoapp.domain.provideLoginUseCase
import com.learn.todoapp.presentation.create.CreateOrUpdateTodoViewModel
import com.learn.todoapp.presentation.home.HomeViewModel
import com.learn.todoapp.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun getDataModules() = module {
    single { provideRetrofit() }

    //Api
    factory { provideTodoApi(get()) }

    //DB
    //single { provideRoomDatabase(androidContext()) }
    //single { provideTodoDao(get()) }

    //Repos
    factory { provideLoginRepository(get()) }

}

fun getDomainModules() = module {
    factory { provideLoginUseCase(get()) }
    factory { provideFetchAllTodosUseCase() }
}

fun getViewModules() = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel() }
    viewModel { CreateOrUpdateTodoViewModel() }

}