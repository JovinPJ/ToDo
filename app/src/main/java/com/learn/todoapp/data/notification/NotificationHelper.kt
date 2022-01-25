package com.learn.todoapp.data.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.learn.todoapp.R
import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.data.notification.NotificationConstants.FULL_SCREEN_NOTIFICATION_ACTION
import com.learn.todoapp.data.notification.NotificationConstants.GROUP_KEY_TODO
import com.learn.todoapp.data.notification.NotificationConstants.KEY_TODO_EXTRA
import com.learn.todoapp.data.notification.NotificationConstants.NOTIFICATION_CHANNEL_ID
import com.learn.todoapp.data.notification.NotificationConstants.NOTIFICATION_FULL_SCREEN_REQUEST_CODE
import com.learn.todoapp.data.notification.FullScreenNotificationActivity

class NotificationHelper(private val context: Context) {

    fun showNotification(alarmToDo: AlarmToDo) {
        val builder = generateNotificationBuilder(alarmToDo)
        with(NotificationManagerCompat.from(context)) {
            notify(alarmToDo.id, builder.build())
        }
    }


    private fun generateNotificationBuilder(alarmToDo: AlarmToDo): NotificationCompat.Builder {
        createNotificationChannel()

        val fullScreenIntent = getNotificationIntent(alarmToDo)

        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(fullScreenIntent)
            getPendingIntent(
                NOTIFICATION_FULL_SCREEN_REQUEST_CODE,
                getFlag()
            )
        }

        return NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_today)
            .setContentTitle(alarmToDo.title)
            .setContentText(alarmToDo.description)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(alarmToDo.description)
            )
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentIntent(resultPendingIntent)
            .setFullScreenIntent(resultPendingIntent, true)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setVisibility(VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .setAutoCancel(true)
            .setGroup(GROUP_KEY_TODO)
    }

    private fun getNotificationIntent(alarmToDo: AlarmToDo): Intent {
        return Intent(context, FullScreenNotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            action = FULL_SCREEN_NOTIFICATION_ACTION
            putExtra(KEY_TODO_EXTRA, alarmToDo)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getFlag() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    else PendingIntent.FLAG_UPDATE_CURRENT

}