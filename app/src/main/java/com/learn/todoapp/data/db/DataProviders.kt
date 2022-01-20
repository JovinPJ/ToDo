package com.learn.todoapp.data.db

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.learn.todoapp.data.repositoryImpl.PreferenceRepositoryImpl
import com.learn.todoapp.domain.repositories.PreferenceRepository

fun providePreference(context: Context): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(context)
}

fun providePreferenceRepository(sharedPreferences: SharedPreferences): PreferenceRepository =
    PreferenceRepositoryImpl(sharedPreferences)