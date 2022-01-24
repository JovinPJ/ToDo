package com.learn.todoapp

import android.app.Application
import com.learn.todoapp.presentation.di.getAlarmModules
import com.learn.todoapp.presentation.di.getDataModules
import com.learn.todoapp.presentation.di.getDomainModules
import com.learn.todoapp.presentation.di.getViewModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoinDependencies()
    }

    private fun initializeKoinDependencies() {

        startKoin {
            //inject Android context
            androidContext(this@TodoApplication)
            // use modules
            modules(
                getDataModules(),
                getDomainModules(),
                getViewModules(),
                getAlarmModules()
            )
        }

    }

}