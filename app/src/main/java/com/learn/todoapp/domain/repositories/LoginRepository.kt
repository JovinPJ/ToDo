package com.learn.todoapp.domain.repositories

import com.learn.todoapp.domain.models.LoginResponse

interface LoginRepository {

    suspend fun login(email: String, password: String): LoginResponse

}