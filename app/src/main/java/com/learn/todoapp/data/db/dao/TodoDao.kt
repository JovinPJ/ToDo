package com.learn.todoapp.data.db.dao

import androidx.room.*
import com.learn.todoapp.data.db.tables.TodoTables

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoTables: TodoTables)

    @Query("SELECT * FROM Todo WHERE `userToken` = :userToken")
    suspend fun fetchTodos(userToken: String): List<TodoTables>

    @Query("SELECT * FROM Todo")
    suspend fun fetchAllTodos(): List<TodoTables>

    @Query("SELECT * FROM Todo WHERE `userToken` = :userToken AND `id` = :id ")
    suspend fun fetchTodo(userToken: String, id: Int): TodoTables

    @Query("SELECT * FROM Todo ORDER BY `id` DESC LIMIT 1")
    suspend fun fetchLastTodo(): TodoTables

    @Update
    suspend fun updateTodo(todoTables: TodoTables)

    @Query("UPDATE Todo SET alarmTime=:alarmTime WHERE id = :id")
    suspend fun updateTodoAlarmTime(id: Int, alarmTime: Long)

    @Query("DELETE FROM Todo")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteTodo(todoTables: TodoTables)
}