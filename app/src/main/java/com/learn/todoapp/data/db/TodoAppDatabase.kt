package com.learn.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.learn.todoapp.data.db.dao.TodoDao
import com.learn.todoapp.data.db.tables.TodoTables


@Database(entities = [TodoTables::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoAppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}