package com.learn.todoapp.data.alarm

import android.app.AlarmManager
import android.content.Context
import com.learn.todoapp.data.repositoryImpl.AlarmRepositoryImpl
import com.learn.todoapp.domain.repositories.AlarmRepository


fun provideAlarmManager(context: Context): AlarmManager {
    return context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
}

fun provideAlarmHelper(context: Context, alarmManager: AlarmManager): AlarmHelper =
    AlarmHelper(context, alarmManager)

fun provideAlarmRepository(alarmHelper: AlarmHelper): AlarmRepository =
    AlarmRepositoryImpl(alarmHelper)