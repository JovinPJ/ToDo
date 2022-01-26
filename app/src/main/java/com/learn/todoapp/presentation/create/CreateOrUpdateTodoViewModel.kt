package com.learn.todoapp.presentation.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learn.todoapp.R
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.models.ToDoType
import com.learn.todoapp.domain.usecases.FetchTodoUsecase
import com.learn.todoapp.domain.usecases.InsertTodoUsecase
import com.learn.todoapp.presentation.base.BaseViewModel
import com.learn.todoapp.presentation.utils.isTodoTitleValid
import com.learn.todoapp.presentation.utils.models.ValidationResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateOrUpdateTodoViewModel(
    private val insertTodoUsecase: InsertTodoUsecase,
    private val fetchTodoUsecase: FetchTodoUsecase,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    private val todoInsertedLiveData = MutableLiveData<Long>()
    private val todoLiveData = MutableLiveData<ToDo>()
    fun getTodoInsertedLiveData(): LiveData<Long> = todoInsertedLiveData
    fun getTodoLiveData(): LiveData<ToDo> = todoLiveData

    private var todoId: Int = 0  // Id will be updated on UpdateMode

    fun insertOrUpdateTodo(
        title: String, desc: String?, hour: Int, minute: Int, date: Long?, toDoType: ToDoType
    ) {
        if (!isTodoTitleValid(title)) return
        try {
            showProgress()
            viewModelScope.launch(coroutineDispatcher + handler) {
                val alarmTriggerTime = insertTodoUsecase.insertOrUpdateTodo(
                    ToDo(todoId, title, desc, hour, minute, date, toDoType)
                )
                todoInsertedLiveData.postValue(alarmTriggerTime)
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

    private fun isTodoTitleValid(title: String): Boolean {
        val isValid = title.isTodoTitleValid()
        return if (isValid is ValidationResponse.ValidationFailure) {
            showToast(toastRes = isValid.msgRes)
            false
        } else true
    }

    fun fetchTodo(id: Int) {
        try {
            showProgress()
            viewModelScope.launch(coroutineDispatcher + handler) {
                fetchTodoUsecase.fetch(id)?.let {
                    todoLiveData.postValue(it)
                    todoId = it.id
                    hideProgress()
                }
            }
        } catch (e: Exception) {
            Log.e("CreateTodoViewModel", "fetch Todo Caught $e")
            hideProgress()
            e.message?.let {
                showToast(e.message)
            } ?: showToast(toastRes = R.string.unknown_error)

        }

    }

}