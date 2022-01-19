package com.learn.todoapp.presentation.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {
    protected val handler = CoroutineExceptionHandler { _, exception ->
        val msg = "Caught $exception"
        Log.e("ExceptionHandler", msg)
        showToast(msg)
    }

    private val toastMessageLiveData = MutableLiveData<String>()
    private val progressBarLiveData = MutableLiveData<Boolean>()

    fun getToastLiveData(): LiveData<String> {
        return toastMessageLiveData
    }

    fun getProgressBarLiveData(): LiveData<Boolean> {
        return progressBarLiveData
    }

    protected fun showToast(toastMsg: String) {
        toastMessageLiveData.postValue(toastMsg)
    }

    protected fun showProgress() {
        progressBarLiveData.postValue(true)
    }

    protected fun hideProgress() {
        progressBarLiveData.postValue(false)
    }
}