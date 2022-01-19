package com.learn.todoapp.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.todoapp.domain.usecases.LoginUsecase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUsecase: LoginUsecase) : ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("LoginViewModel", "Caught $exception")
    }

    fun login(email: String, password: String) {
        try {
            viewModelScope.launch(Dispatchers.IO + handler) {
                loginUsecase.login(email, password)
            }
        } catch (e: Exception) {
            Log.e("LoginViewModel", "login api Caught $e")
        }
    }

}