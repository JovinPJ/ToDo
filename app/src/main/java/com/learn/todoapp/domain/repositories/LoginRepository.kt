package com.learn.todoapp.domain.repositories

interface LoginRepository {

    fun login(email: String, password: String)

}