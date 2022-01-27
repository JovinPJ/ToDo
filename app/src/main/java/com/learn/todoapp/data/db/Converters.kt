package com.learn.todoapp.data.db

import androidx.room.TypeConverter
import com.learn.todoapp.domain.models.ToDoType

class Converters {
    @TypeConverter
    fun toTodoType(value: String) = enumValueOf<ToDoType>(value)

    @TypeConverter
    fun fromTodoType(value: ToDoType) = value.name

}