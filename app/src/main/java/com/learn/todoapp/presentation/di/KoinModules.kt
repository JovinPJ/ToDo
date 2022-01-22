package com.learn.todoapp.presentation.di

import com.learn.todoapp.data.api.provideRetrofit
import com.learn.todoapp.data.api.provideTodoApi
import com.learn.todoapp.data.db.*
import com.learn.todoapp.data.repositoryImpl.provideLoginRepository
import com.learn.todoapp.domain.*
import com.learn.todoapp.presentation.create.CreateOrUpdateTodoViewModel
import com.learn.todoapp.presentation.home.HomeViewModel
import com.learn.todoapp.presentation.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun getDataModules() = module {
    single { provideRetrofit() }

    //Api
    factory { provideTodoApi(get()) }

    //DB
    single { provideTodoRoomDatabase(androidContext()) }
    single { provideTodoDao(get()) }

    //Preference
    single { providePreference(androidContext()) }

    //Repos
    factory { provideLoginRepository(get()) }
    factory { providePreferenceRepository(get()) }
    factory { provideTodoDBOperationRepository(get()) }

}

fun getDomainModules() = module {
    factory { provideLoginUseCase(get(), get()) }
    factory { provideFetchTodosUseCase(get(), get()) }
    factory { provideUserTokenUseCase(get()) }
    factory { provideInsertTodoUseCase(get(), get()) }
    factory { provideUpdateTodoUseCase(get(), get()) }
    factory { provideDeleteTodoUseCase(get(), get()) }
}

fun getViewModules() = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { CreateOrUpdateTodoViewModel(get(), get(), get()) }
}