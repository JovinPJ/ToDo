package com.learn.todoapp.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learn.todoapp.databinding.CreateOrUpdateTodoFragmentBinding
import com.learn.todoapp.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateOrUpdateTodoFragment : BaseFragment() {

    private val viewModel: CreateOrUpdateTodoViewModel by viewModel()
    private lateinit var binding: CreateOrUpdateTodoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CreateOrUpdateTodoFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}