package com.learn.todoapp.data.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.learn.todoapp.domain.models.ToDoType

@Entity(tableName = "Todo")
data class TodoTables(
    @PrimaryKey val title: String,
    val userToken: String = "",
    val description: String? = null,
    val hour: Int = 0,
    val minute: Int = 0,
    val date: Long? = null,
    val type: ToDoType = ToDoType.DAILY
)
