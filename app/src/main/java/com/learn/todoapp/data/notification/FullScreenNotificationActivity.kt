package com.learn.todoapp.data.notification

import android.media.Ringtone
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learn.todoapp.data.alarm.mappers.toDomain
import com.learn.todoapp.data.alarm.model.AlarmToDo
import com.learn.todoapp.data.notification.NotificationConstants.KEY_TODO_EXTRA
import com.learn.todoapp.databinding.ActivityFullScreenBinding

class FullScreenNotificationActivity : AppCompatActivity() {

    private var ringtone: Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFullScreenBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.todo = intent?.getParcelableExtra<AlarmToDo>(KEY_TODO_EXTRA)?.toDomain()
        setContentView(binding.root)
        turnScreenOnAndKeyguardOff()
    }

    override fun onResume() {
        super.onResume()
        ringtone = playAlarm()

    }

    override fun onPause() {
        super.onPause()
        ringtone?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        turnScreenOffAndKeyguardOn()
    }
}