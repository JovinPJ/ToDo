package com.learn.todoapp.data.db

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.learn.todoapp.data.db.Constants.DBConstants.DB_NAME
import com.learn.todoapp.data.db.dao.TodoDao
import com.learn.todoapp.data.repositoryImpl.PreferenceRepositoryImpl
import com.learn.todoapp.data.repositoryImpl.ToDoDbOperationsRepositoryImpl
import com.learn.todoapp.domain.repositories.PreferenceRepository
import com.learn.todoapp.domain.repositories.TodoDbOperationsRepository

fun providePreference(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun providePreferenceRepository(sharedPreferences: SharedPreferences): PreferenceRepository =
    PreferenceRepositoryImpl(sharedPreferences)

fun provideTodoRoomDatabase(context: Context): TodoAppDatabase {
    return Room.databaseBuilder(context, TodoAppDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .fallbackToDestructiveMigration()
        .build()
}

fun provideTodoDao(roomDB: TodoAppDatabase): TodoDao {
    return roomDB.todoDao()
}

fun provideTodoDBOperationRepository(todoDao: TodoDao): TodoDbOperationsRepository =
    ToDoDbOperationsRepositoryImpl(todoDao)