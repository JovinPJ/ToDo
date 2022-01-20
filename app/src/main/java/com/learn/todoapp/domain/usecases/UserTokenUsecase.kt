package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.repositories.PreferenceRepository

class UserTokenUsecase(private val preferenceRepository: PreferenceRepository) {

    suspend fun getToken(): String? {
        return preferenceRepository.getUserToken()
    }

    suspend fun clearToken() {
        preferenceRepository.clearUserToken()
    }
}