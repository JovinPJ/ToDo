package com.learn.todoapp.presentation.home

import com.learn.todoapp.domain.models.ToDo

interface TodoItemClickListener {
    fun onClick(todo: ToDo)
    fun onLongClick(todo: ToDo): Boolean
}