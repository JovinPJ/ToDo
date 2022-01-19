package com.learn.todoapp.presentation.utils

import android.content.Context
import android.widget.Toast


fun Context.showToast(
    message: String? = null,
    isLong: Boolean = false,
    messageRes: Int? = null
) {

    Toast.makeText(
        this,
        message
            ?: messageRes?.let { this.getString(it) }
            ?: throw Exception("Should provide either message or message resId for Toast"),
        if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    ).show()
}

