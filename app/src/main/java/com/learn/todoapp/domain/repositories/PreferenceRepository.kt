package com.learn.todoapp.domain.repositories

interface PreferenceRepository {

    suspend fun getUserToken(): String?
    suspend fun saveUserToken(userToken: String)
    suspend fun clearUserToken()

}