package com.learn.todoapp.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learn.todoapp.R
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.domain.usecases.FetchAllTodosUsecase
import com.learn.todoapp.domain.usecases.UserTokenUsecase
import com.learn.todoapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userTokenUsecase: UserTokenUsecase,
    private val fetchAllTodosUsecase: FetchAllTodosUsecase
) : BaseViewModel() {

    private val todosListLiveData = MutableLiveData<List<ToDo>>()
    fun getTodosListLiveData(): LiveData<List<ToDo>> = todosListLiveData

    fun logOut() { // remove user Token
        viewModelScope.launch {
            userTokenUsecase.clearToken()
        }
    }

    fun fetchAllTodos() { // remove user Token

        try {
            showProgress()
            viewModelScope.launch(Dispatchers.IO + handler) {
                val todos = fetchAllTodosUsecase.fetchAll()
                todosListLiveData.postValue(todos)
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