package com.learn.todoapp.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.learn.todoapp.databinding.CreateOrUpdateTodoFragmentBinding
import com.learn.todoapp.domain.models.ToDoType
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        binding.fabCreateTodo.setOnClickListener {
            viewModel.insertTodo(
                binding.etTitle.text.toString(),
                binding.etDescription.text.toString(),
                binding.tvTime.text.toString(),
                binding.tvDate.text.toString(),
                if (binding.rgTodoType.checkedRadioButtonId == binding.radioDaily.id)
                    ToDoType.DAILY else ToDoType.WEEKLY
            )
        }
    }

    private fun setObservers() {
        setBaseObserver(viewModel)
        viewModel.getTodoInsertedLiveData().observe(viewLifecycleOwner) { isInserted ->
            if (isInserted) findNavController().popBackStack()
        }
    }

}