package com.learn.todoapp.data.notification

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.view.WindowManager

fun Activity.turnScreenOnAndKeyguardOff() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
        setShowWhenLocked(true)
        setTurnScreenOn(true)
    } else {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
    }

    with(getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requestDismissKeyguard(this@turnScreenOnAndKeyguardOff, null)
        }
    }
}

fun Activity.turnScreenOffAndKeyguardOn() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
        setShowWhenLocked(false)
        setTurnScreenOn(false)
    } else {
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
        )
    }
}

fun Activity.playAlarm(): Ringtone? {
    val alert: Uri? = getAlarm()
    var ringtone: Ringtone? = null
    alert?.let {
        ringtone = RingtoneManager.getRingtone(applicationContext, alert)
        ringtone?.play()
    }
    return ringtone
}

fun getAlarm(): Uri? {
    var alert: Uri? = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

    if (alert == null) {
        alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        if (alert == null) {
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        }
    }
    return alert
}