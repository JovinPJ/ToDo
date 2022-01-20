package com.learn.todoapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.learn.todoapp.databinding.LoginFragmentBinding
import com.learn.todoapp.presentation.base.BaseFragment
import com.learn.todoapp.presentation.utils.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        binding.btSubmit.setOnClickListener {
            activity?.hideKeyboard()
            viewModel.login(
                binding.etEmailId.text.toString(),
                binding.etPasswordId.text.toString()
            )
        }
    }

    private fun setObservers() {
        setBaseObserver(viewModel)
        viewModel.getLoggedInLiveData().observe(viewLifecycleOwner) { isLoggedIn ->
            navigateToHome(isLoggedIn)
        }
    }

    private fun navigateToHome(loggedIn: Boolean?) {
        if (loggedIn == true) {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

}