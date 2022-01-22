package com.learn.todoapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.learn.todoapp.R
import com.learn.todoapp.databinding.HomeFragmentBinding
import com.learn.todoapp.domain.models.ToDo
import com.learn.todoapp.presentation.base.BaseFragment
import com.learn.todoapp.presentation.utils.showMsgDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), TodoItemClickListener {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPress()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.adapter = TodoAdapter(this)
        activity?.title = getString(R.string.home_title)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        viewModel.fetchAllTodos()
        binding.fabCreateTodo.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCreateOrUpdateTodoFragment()
            it.findNavController().navigate(action)
        }
    }

    private fun setObservers() {
        setBaseObserver(viewModel)
        viewModel.getTodosListLiveData().observe(viewLifecycleOwner) { todoList ->
            (binding.rvTodoList.adapter as TodoAdapter).submitList(todoList)
        }
    }

    private fun handleBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.logOut()
                isEnabled = false
                activity?.onBackPressed()
            }
        })
    }

    override fun onClick(todo: ToDo) {

    }

    override fun onLongClick(todo: ToDo): Boolean {
        activity?.showMsgDialog(
            title = getString(R.string.delete),
            message = getString(R.string.delete_confirm),
            negativeButtonTxt = getString(R.string.cancel)
        ) { viewModel.deleteTodo(todo) }
        return true
    }


}