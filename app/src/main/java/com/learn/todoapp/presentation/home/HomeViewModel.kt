package com.learn.todoapp.presentation.home

import androidx.lifecycle.viewModelScope
import com.learn.todoapp.domain.usecases.FetchAllTodosUsecase
import com.learn.todoapp.domain.usecases.UserTokenUsecase
import com.learn.todoapp.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val userTokenUsecase: UserTokenUsecase,
    private val fetchAllTodosUsecase: FetchAllTodosUsecase
) : BaseViewModel() {
    fun logOut() { // remove user Token
        viewModelScope.launch {
            userTokenUsecase.clearToken()
        }
    }
}