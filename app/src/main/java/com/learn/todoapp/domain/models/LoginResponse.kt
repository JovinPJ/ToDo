package com.learn.todoapp.domain.models

data class LoginResponse(
    val isSuccess: Boolean,
    val token: String? = null,
    val errorMsg: String? = null
)
