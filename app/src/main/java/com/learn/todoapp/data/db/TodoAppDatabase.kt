package com.learn.todoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.learn.todoapp.data.db.dao.TodoDao
import com.learn.todoapp.data.db.tables.TodoTables


@Database(entities = [TodoTables::class], version = 2)
@TypeConverters(Converters::class)
abstract class TodoAppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Todo ADD COLUMN alarmTime INTEGER")
    }
}