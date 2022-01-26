package com.learn.todoapp.data.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.learn.todoapp.data.alarm.TodoAlarmConstants.KEY_TODO
import com.learn.todoapp.data.alarm.TodoAlarmConstants.TODO_ALARM_ACTION
import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.data.alarm.utils.getAlarmTimeInterval
import com.learn.todoapp.data.alarm.utils.getAlarmTriggerStartDate
import com.learn.todoapp.data.alarm.utils.getUpdateAlarmTriggerStartTime
import com.learn.todoapp.data.alarm.utils.getUpdateIntervalMillis

class AlarmHelper(private val context: Context, private val alarmManager: AlarmManager) {

    fun registerAlarm(alarmToDo: AlarmToDo): Long {
        var alarmTriggerTime = 0L
        val pendingIntent = getPendingIntent(alarmToDo)
        pendingIntent?.let {
            val alarmTriggerDate = alarmToDo.getAlarmTriggerStartDate()
            val alarmTimeInterval = alarmToDo.getAlarmTimeInterval()
            alarmTriggerTime = alarmTriggerDate + alarmTimeInterval
            registerAlarm(alarmTriggerTime, pendingIntent)
            Log.e(
                "Alarm", "Registered ${alarmToDo.id} for Time $alarmTimeInterval \n" +
                        "Alarm Trigger Start Date $alarmTriggerDate \n" +
                        "Final Alarm Trigger Time : $alarmTriggerTime"
            )
        }
        return alarmTriggerTime
    }

    fun updateAlarm(alarmToDo: AlarmToDo): Long {
        var alarmTriggerTime = 0L
        val pendingIntent = getPendingIntent(alarmToDo)
        pendingIntent?.let {
            val alarmTimeInterval = alarmToDo.toDoType.getUpdateIntervalMillis()
            val alarmTriggerStartTime =
                getUpdateAlarmTriggerStartTime( // used to avoid 'AlarmManager delays'
                    alarmToDo.hour,
                    alarmToDo.minute
                )
            alarmTriggerTime = alarmTriggerStartTime + alarmTimeInterval
            registerAlarm(alarmTriggerTime, pendingIntent)
            Log.e(
                "Alarm", "Registered ${alarmToDo.id} for Time $alarmTimeInterval \n" +
                        "Alarm Trigger Start Date $alarmTriggerStartTime \n" +
                        "Final Alarm Trigger Time : $alarmTriggerTime"
            )
        }
        return alarmTriggerTime
    }


    fun cancelAlarm(alarmToDo: AlarmToDo) {
        val pendingIntent = getPendingIntent(alarmToDo)
        pendingIntent?.let {
            alarmManager.cancel(it)
        }
    }

    private fun registerAlarm(
        alarmTriggerTime: Long,
        pendingIntent: PendingIntent?
    ) {
        alarmManager.setAlarmClock( // using setAlarmClock method to trigger on Exact Time
            AlarmManager.AlarmClockInfo(
                alarmTriggerTime,
                pendingIntent
            ),
            pendingIntent
        )
    }


    private fun getTodoAlarmIntent(todo: AlarmToDo): Intent {
        val intent = Intent(context, TodoAlarmReceiver::class.java)
        intent.action = TODO_ALARM_ACTION
        intent.putExtra(KEY_TODO, todo)
        return intent
    }

    private fun getPendingIntent(todo: AlarmToDo) =
        PendingIntent.getBroadcast(
            context,
            todo.id,
            getTodoAlarmIntent(todo),
            getFlag()
        )

    private fun getFlag() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    else PendingIntent.FLAG_UPDATE_CURRENT

    fun reRegisterAllAlarms() {

    }

}