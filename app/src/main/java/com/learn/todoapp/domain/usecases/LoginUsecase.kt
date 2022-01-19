package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.models.LoginResponse
import com.learn.todoapp.domain.repositories.LoginRepository

class LoginUsecase(private val loginRepository: LoginRepository) {

    suspend fun login(email: String, password: String): LoginResponse {
        val loginResponse = loginRepository.login(email, password)
        loginResponse.token?.let {
            //save Token
        }
        return loginResponse
    }
}