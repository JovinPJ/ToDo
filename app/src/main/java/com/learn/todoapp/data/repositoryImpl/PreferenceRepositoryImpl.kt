package com.learn.todoapp.data.repositoryImpl

import android.content.SharedPreferences
import com.learn.todoapp.data.db.Constants.PreferenceConstants.USER_TOKEN
import com.learn.todoapp.domain.repositories.PreferenceRepository

class PreferenceRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    PreferenceRepository {

    override suspend fun getUserToken(): String? {
        return sharedPreferences.getString(USER_TOKEN, null)
    }

    override suspend fun saveUserToken(userToken: String) {
        sharedPreferences.edit().putString(USER_TOKEN, userToken).apply()
    }

    override suspend fun clearUserToken() {
        sharedPreferences.edit().remove(USER_TOKEN).apply()
    }
}