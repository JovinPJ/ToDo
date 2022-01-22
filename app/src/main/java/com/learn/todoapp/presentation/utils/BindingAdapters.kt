package com.learn.todoapp.presentation.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learn.todoapp.domain.models.ToDo

@BindingAdapter("isVisible")
fun View.showHide(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["setAdapter"])
fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
    setHasFixedSize(true)
    this.adapter = adapter
}

@BindingAdapter(value = ["displayTime"])
fun TextView.displayTime(todo: ToDo) {
    text = displayTime(todo.hour, todo.minute)
}

@BindingAdapter(value = ["displayDate"])
fun TextView.displayDate(date: Long?) {
    text = date?.toFormattedDateText()
}