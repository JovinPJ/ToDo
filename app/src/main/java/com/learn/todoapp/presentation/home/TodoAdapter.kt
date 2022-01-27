package com.learn.todoapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.learn.todoapp.databinding.TodoItemBinding
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.models.ToDoType

class TodoAdapter(private val todoItemClickListener: TodoItemClickListener) :
    ListAdapter<ToDo, TodoAdapter.TodoViewHolder>(Companion) {

    class TodoViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean = oldItem === newItem
        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean =
            oldItem.title == newItem.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TodoItemBinding.inflate(layoutInflater, parent, false)

        return TodoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = getItem(position)
        holder.binding.todo = currentTodo
        holder.binding.todoItemClick = todoItemClickListener
        val selectedTypeId = when (currentTodo.toDoType) {
            ToDoType.WEEKLY -> holder.binding.radioWeekly.id
            ToDoType.DAILY -> holder.binding.radioDaily.id
        }
        holder.binding.rgTodoType.check(selectedTypeId)
        holder.binding.executePendingBindings()
    }
}