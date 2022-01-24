package com.learn.todoapp.data.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.learn.todoapp.data.alarm.TodoAlarmConstants.BOOT_COMPLETED
import com.learn.todoapp.data.alarm.TodoAlarmConstants.KEY_TODO
import com.learn.todoapp.data.alarm.TodoAlarmConstants.TIME_SET
import com.learn.todoapp.data.alarm.TodoAlarmConstants.TODO_ALARM_ACTION
import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.presentation.utils.ALARM_TIME_DISPLAY_FORMAT
import com.learn.todoapp.presentation.utils.toFormattedDateText
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodoAlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val alarmHelper: AlarmHelper by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            TODO_ALARM_ACTION -> {
                val alarmToDo = intent.getParcelableExtra<AlarmToDo>(KEY_TODO)
                alarmToDo?.let {
                    Log.i("Alarm", "Alarm Type : ${it.toDoType}")
                    val alarmTime = alarmHelper.updateAlarm(it)
                    showToast(alarmTime, context)
                }
            }
            BOOT_COMPLETED -> {
                alarmHelper.reRegisterAllAlarms()
            }
            TIME_SET -> {
                alarmHelper.reRegisterAllAlarms()
            }

        }
    }

    private fun showToast(alarmTime: Long, context: Context?) {
        if (alarmTime > 0)
            Toast.makeText(
                context,
                "Alarm set on \n" +
                        alarmTime.toFormattedDateText(
                            ALARM_TIME_DISPLAY_FORMAT
                        ), Toast.LENGTH_SHORT
            ).show()
    }
}