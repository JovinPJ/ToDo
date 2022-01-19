package com.learn.todoapp.domain.usecases

import com.learn.todoapp.domain.repositories.LoginRepository

class LoginUsecase(private val loginRepository: LoginRepository) {

    fun login(email: String, password: String) {
        loginRepository.login(email, password)
    }
}