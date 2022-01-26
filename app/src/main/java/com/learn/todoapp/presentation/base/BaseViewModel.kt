package com.learn.todoapp.presentation.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learn.todoapp.R
import com.learn.todoapp.presentation.utils.models.ToastMessage
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {
    protected val handler = CoroutineExceptionHandler { _, exception ->
        val msg = "Caught $exception"
        hideProgress()
        if (exception is UnknownHostException)
            showToast(toastRes = R.string.check_internet)
        else showToast(toastMsg = msg)
    }

    private val toastMessageLiveData = MutableLiveData<ToastMessage>()
    private val progressBarLiveData = MutableLiveData<Boolean>()

    fun getToastLiveData(): LiveData<ToastMessage> {
        return toastMessageLiveData
    }

    fun getProgressBarLiveData(): LiveData<Boolean> {
        return progressBarLiveData
    }

    protected fun showToast(toastMsg: String? = null, toastRes: Int? = null) {
        toastMessageLiveData.postValue(
            ToastMessage(
                message = toastMsg,
                messageRes = toastRes
            )
        )
    }

    protected fun showProgress() {
        progressBarLiveData.postValue(true)
    }

    protected fun hideProgress() {
        progressBarLiveData.postValue(false)
    }
}