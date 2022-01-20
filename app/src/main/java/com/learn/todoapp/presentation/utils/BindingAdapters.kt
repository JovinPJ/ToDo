package com.learn.todoapp.presentation.utils

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}