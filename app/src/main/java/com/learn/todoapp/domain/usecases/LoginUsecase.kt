package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.LoginResponse
import com.learn.todoapp.domain.repositories.LoginRepository
import com.learn.todoapp.domain.repositories.PreferenceRepository

class LoginUsecase(
    private val loginRepository: LoginRepository,
    private val preferenceRepository: PreferenceRepository,
) {

    suspend fun login(email: String, password: String): LoginResponse {
        val loginResponse = loginRepository.login(email, password)
        loginResponse.token?.let {
            //save Token
            preferenceRepository.saveUserToken(it)
        }
        return loginResponse
    }
}