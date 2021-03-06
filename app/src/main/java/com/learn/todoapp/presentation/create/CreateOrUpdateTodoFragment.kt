package com.learn.todoapp.presentation.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.learn.todoapp.R
import com.learn.todoapp.databinding.CreateOrUpdateTodoFragmentBinding
import com.learn.todoapp.domain.models.ToDoType
import com.learn.todoapp.presentation.base.BaseFragment
import com.learn.todoapp.presentation.utils.ALARM_TIME_DISPLAY_FORMAT
import com.learn.todoapp.presentation.utils.displayTime
import com.learn.todoapp.presentation.utils.showToast
import com.learn.todoapp.presentation.utils.toFormattedDateText
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CreateOrUpdateTodoFragment : BaseFragment() {

    private val viewModel: CreateOrUpdateTodoViewModel by viewModel()
    private lateinit var binding: CreateOrUpdateTodoFragmentBinding

    private val rightNow = Calendar.getInstance()

    private var selectedDate: Long = rightNow.timeInMillis
    private var selectedHour: Int = rightNow.get(Calendar.HOUR_OF_DAY)
    private var selectedMinute: Int = rightNow.get(Calendar.MINUTE)

    companion object {
        const val ARG_ID = "arg_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = arguments?.getInt(ARG_ID)?.let {
            if (it > 0) { // update mode
                viewModel.fetchTodo(it)
                getString(R.string.update_todo)
            } else getString(R.string.create_todo)
        } ?: kotlin.run { getString(R.string.create_todo) }
        activity?.title = title
    }

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
        initializeViews()
        binding.fabCreateTodo.setOnClickListener {
            insertOrUpdateTodo()
        }
        binding.tvDate.setOnClickListener {
            showDatePicker()
        }
        binding.tvTime.setOnClickListener {
            showTimePicker()
        }
    }

    private fun initializeViews() {
        binding.tvDate.text = selectedDate.toFormattedDateText()
        binding.tvTime.text = displayTime(selectedHour, selectedMinute)
    }

    private fun setObservers() {
        setBaseObserver(viewModel)
        viewModel.getTodoInsertedLiveData().observe(viewLifecycleOwner) { alarmTime ->

            if (alarmTime > 0) showToast(
                getString(
                    R.string.alarm_set_on, alarmTime.toFormattedDateText(
                        ALARM_TIME_DISPLAY_FORMAT
                    )
                )
            )
            findNavController().popBackStack()
        }
        viewModel.getTodoLiveData().observe(viewLifecycleOwner) { todo ->
            todo.date?.let {
                selectedDate = it
            }
            selectedHour = todo.hour
            selectedMinute = todo.minute

            val selectedTypeId = when (todo.toDoType) {
                ToDoType.WEEKLY -> binding.radioWeekly.id
                ToDoType.DAILY -> binding.radioDaily.id
            }
            binding.rgTodoType.check(selectedTypeId)
        }
    }

    private fun insertOrUpdateTodo() {
        viewModel.insertOrUpdateTodo(
            binding.etTitle.text.toString(),
            binding.etDescription.text.toString(),
            selectedHour,
            selectedMinute,
            selectedDate,
            if (binding.rgTodoType.checkedRadioButtonId == binding.radioDaily.id)
                ToDoType.DAILY else ToDoType.WEEKLY
        )
    }

    private fun showDatePicker() {
        val selectedDateInMillis = selectedDate
        val constraintsBuilder =
            CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now())
        MaterialDatePicker.Builder.datePicker()
            .setSelection(selectedDateInMillis)
            .setCalendarConstraints(constraintsBuilder.build())
            .build().apply {
                addOnPositiveButtonClickListener { dateInMillis -> onDateSelected(dateInMillis) }
            }.show(childFragmentManager, MaterialDatePicker::class.java.canonicalName)
    }

    private fun onDateSelected(dateTimeStampInMillis: Long) {
        selectedDate = dateTimeStampInMillis
        binding.tvDate.text = dateTimeStampInMillis.toFormattedDateText()
    }


    private fun showTimePicker() {

        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(selectedHour)
            .setMinute(selectedMinute)
            .build()
            .apply {
                addOnPositiveButtonClickListener { onTimeSelected(this.hour, this.minute) }
            }.show(childFragmentManager, MaterialTimePicker::class.java.canonicalName)
    }

    private fun onTimeSelected(hour: Int, minute: Int) {
        selectedHour = hour
        selectedMinute = minute
        binding.tvTime.text = displayTime(hour, minute)
    }

}