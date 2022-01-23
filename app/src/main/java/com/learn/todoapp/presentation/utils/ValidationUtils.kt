package com.learn.todoapp.presentation.utils

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.learn.todoapp.R
import com.learn.todoapp.presentation.utils.models.ValidationResponse

fun String?.isEmailValid(): ValidationResponse {

    return when {
        isNullOrEmpty() -> ValidationResponse.ValidationFailure(R.string.empty_email)
        !EMAIL_ADDRESS.matcher(this).matches() ->
            ValidationResponse.ValidationFailure(R.string.invalid_email)
        else -> ValidationResponse.ValidationSuccess
    }
}

fun String?.isPasswordValid(): ValidationResponse {
    return when {
        isNullOrEmpty() -> ValidationResponse.ValidationFailure(R.string.empty_password)
        length < 5 ->
            ValidationResponse.ValidationFailure(R.string.password_min_length)
        else -> ValidationResponse.ValidationSuccess

    }
}

fun String?.isTodoTitleValid(): ValidationResponse {
    return when {
        isNullOrEmpty() -> ValidationResponse.ValidationFailure(R.string.empty_todo_title)
        else -> ValidationResponse.ValidationSuccess

    }
}

