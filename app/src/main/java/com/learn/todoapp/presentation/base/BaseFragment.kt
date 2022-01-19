package com.learn.todoapp.presentation.base

import androidx.fragment.app.Fragment
import com.learn.todoapp.presentation.utils.showToast

abstract class BaseFragment : Fragment() {

    protected fun setBaseObserver(viewModel: BaseViewModel) {
        viewModel.getToastLiveData().observe(viewLifecycleOwner) { toastMsg ->
            showToast(toastMsg)
        }
    }
}