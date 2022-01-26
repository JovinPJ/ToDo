package com.learn.todoapp.data.alarm

import android.app.AlarmManager
import android.content.Context
import com.learn.todoapp.data.db.dao.TodoDao
import com.learn.todoapp.data.repositoryImpl.AlarmRepositoryImpl
import com.learn.todoapp.domain.repositories.AlarmRepository


fun provideAlarmManager(context: Context): AlarmManager {
    return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
}

fun provideAlarmHelper(
    context: Context, alarmManager: AlarmManager,
    todoDao: TodoDao
): AlarmHelper =
    AlarmHelper(context, alarmManager, todoDao)

fun provideAlarmRepository(alarmHelper: AlarmHelper): AlarmRepository =
    AlarmRepositoryImpl(alarmHelper)