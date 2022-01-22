package com.learn.todoapp.presentation.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learn.todoapp.R
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.models.ToDoType
import com.learn.todoapp.domain.usecases.InsertTodoUsecase
import com.learn.todoapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateOrUpdateTodoViewModel(
    private val insertTodoUsecase: InsertTodoUsecase
) : BaseViewModel() {

    private val todoInsertedLiveData = MutableLiveData<Boolean>()
    fun getTodoInsertedLiveData(): LiveData<Boolean> = todoInsertedLiveData

    fun insertTodo(
        title: String, desc: String?, hour: Int, minute: Int, date: Long?, toDoType: ToDoType
    ) {
        try {
            showProgress()
            viewModelScope.launch(Dispatchers.IO + handler) {
                insertTodoUsecase.insertTodo(
                    ToDo(title, desc, hour, minute, date, toDoType)
                )
                todoInsertedLiveData.postValue(true)
                hideProgress()
            }
        } catch (e: Exception) {
            Log.e("CreateTodoViewModel", "insert Todo Caught $e")
            hideProgress()
            e.message?.let {
                showToast(e.message)
            } ?: showToast(toastRes = R.string.unknown_error)

        }

    }

}