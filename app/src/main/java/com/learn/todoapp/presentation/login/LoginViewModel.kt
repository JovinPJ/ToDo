package com.learn.todoapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.todoapp.domain.usecases.LoginUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUsecase: LoginUsecase) : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUsecase.login(email, password)
        }
    }

}