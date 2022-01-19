package com.learn.todoapp.data.api

import com.learn.todoapp.data.api.models.Response
import com.learn.todoapp.data.api.models.User
import retrofit2.http.Body
import retrofit2.http.POST


interface TodoApi {
    @POST("login")
    suspend fun login(@Body user: User): Response
}