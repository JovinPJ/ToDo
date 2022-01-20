package com.learn.todoapp.data.db.dao

import androidx.room.*
import com.learn.todoapp.data.db.tables.TodoTables

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoTables: TodoTables)

    @Query("SELECT * FROM Todo WHERE `userToken` = :userToken")
    fun fetchTodos(userToken: String): List<TodoTables>

    @Update
    suspend fun updateTodo(todoTables: TodoTables)

    @Query("DELETE FROM Todo")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteTodo(vararg todoTables: TodoTables)
}