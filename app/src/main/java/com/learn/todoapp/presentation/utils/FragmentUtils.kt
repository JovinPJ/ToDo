package com.learn.todoapp.presentation.utils

import androidx.fragment.app.Fragment


fun Fragment.showToast(
    message: String? = null,
    isLong: Boolean = false,
    messageRes: Int? = null
) {
    context?.showToast(message, isLong, messageRes)
}