package com.learn.todoapp.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.learn.todoapp.R
import com.learn.todoapp.domain.usecases.LoginUsecase
import com.learn.todoapp.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUsecase: LoginUsecase) : BaseViewModel() {

    private val isLoggedInLiveData = MutableLiveData<Boolean>()

    fun getLoggedInLiveData(): LiveData<Boolean> = isLoggedInLiveData

    fun login(email: String, password: String) {
        try {
            showProgress()
            viewModelScope.launch(Dispatchers.IO + handler) {
                val loginResponse = loginUsecase.login(email, password)
                isLoggedInLiveData.postValue(loginResponse.isSuccess)
                hideProgress()

                if (!loginResponse.isSuccess) {
                    loginResponse.errorMsg?.let {
                        showToast(it)
                    } ?: showToast(toastRes = R.string.login_failed)

                }
            }
        } catch (e: Exception) {
            Log.e("LoginViewModel", "login api Caught $e")
            hideProgress()
            e.message?.let {
                showToast(e.message)
            } ?: showToast(toastRes = R.string.unknown_error)

        }
    }

}