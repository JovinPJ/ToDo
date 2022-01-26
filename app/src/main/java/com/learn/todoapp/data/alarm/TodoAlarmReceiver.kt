package com.learn.todoapp.data.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.learn.todoapp.R
import com.learn.todoapp.data.alarm.TodoAlarmConstants.BOOT_COMPLETED
import com.learn.todoapp.data.alarm.TodoAlarmConstants.KEY_TODO
import com.learn.todoapp.data.alarm.TodoAlarmConstants.TIME_SET
import com.learn.todoapp.data.alarm.TodoAlarmConstants.TODO_ALARM_ACTION
import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.data.notification.NotificationHelper
import com.learn.todoapp.presentation.utils.ALARM_TIME_DISPLAY_FORMAT
import com.learn.todoapp.presentation.utils.toFormattedDateText
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodoAlarmReceiver : BroadcastReceiver(), KoinComponent {

    private val alarmHelper: AlarmHelper by inject()
    private val notificationHelper: NotificationHelper by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            TODO_ALARM_ACTION -> {
                val alarmToDo = intent.getParcelableExtra<AlarmToDo>(KEY_TODO)
                alarmToDo?.let {
                    Log.i("Alarm", "Alarm Type : ${it.toDoType}")
                    CoroutineScope(Dispatchers.IO + handler).launch {
                        val alarmTime = alarmHelper.updateAlarm(it)
                        withContext(Dispatchers.Main) {
                            showToast(alarmTime, context)
                            cancel()
                        }
                    }
                    notificationHelper.showNotification(it)
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

    private val handler = CoroutineExceptionHandler { _, exception ->
        val msg = "Caught $exception"
        Log.e("ExceptionHandler", msg)
    }

    private fun showToast(alarmTime: Long, context: Context?) {
        if (alarmTime > 0 && context != null)
            Toast.makeText(
                context, context.getString(
                    R.string.alarm_at, alarmTime.toFormattedDateText(
                        ALARM_TIME_DISPLAY_FORMAT
                    )
                ), Toast.LENGTH_SHORT
            ).show()
    }
}