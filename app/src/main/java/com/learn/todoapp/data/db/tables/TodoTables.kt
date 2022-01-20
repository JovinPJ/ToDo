package com.learn.todoapp.data.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learn.todoapp.domain.models.ToDoType

@Entity(tableName = "Todo")
data class TodoTables(
    @PrimaryKey val title: String,
    val userToken: String = "",
    val description: String? = null,
    val time: String = "",
    val date: String? = null,
    val type: ToDoType = ToDoType.DAILY
)
