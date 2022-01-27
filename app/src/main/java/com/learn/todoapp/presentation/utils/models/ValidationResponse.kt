package com.learn.todoapp.presentation.utils.models

sealed class ValidationResponse {
    object ValidationSuccess : ValidationResponse()
    data class ValidationFailure(val msgRes: Int) : ValidationResponse()
}
