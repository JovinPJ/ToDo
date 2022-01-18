package com.learn.todoapp.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.learn.todoapp.R

class CreateOrUpdateTodoFragment : Fragment() {

    companion object {
        fun newInstance() = CreateOrUpdateTodoFragment()
    }

    private lateinit var viewModel: CreateOrUpdateTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_or_update_todo_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CreateOrUpdateTodoViewModel::class.java]
    }

}