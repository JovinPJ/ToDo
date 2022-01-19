package com.learn.todoapp.domain.repositories

interface LoginRepository {

    suspend fun login(email: String, password: String)

}