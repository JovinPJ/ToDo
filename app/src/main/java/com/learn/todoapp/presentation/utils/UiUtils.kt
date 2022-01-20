package com.learn.todoapp.presentation.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun Activity.hideKeyboard() {
    try {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = this.currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    } catch (e: Exception) {
    }
}

