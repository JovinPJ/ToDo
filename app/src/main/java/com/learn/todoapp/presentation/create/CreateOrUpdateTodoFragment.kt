package com.learn.todoapp.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.learn.todoapp.databinding.CreateOrUpdateTodoFragmentBinding

class CreateOrUpdateTodoFragment : Fragment() {

    private val viewModel: CreateOrUpdateTodoViewModel by viewModels()
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