package de.j4velin.ledclient

import android.graphics.Color
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        // TODO read from database
        Log.i(TAG, "notification posted: $sbn")
        sbn?.packageName?.let {
            when {
                it.equals("com.google.android.gm", true) -> {
                    Log.i(TAG, "gmail received")
                    trigger(Flash(Color.RED, 1f, 3))
                }
                it.contains("whatsapp", true) -> {
                    Log.i(TAG, "whatsapp received")
                    trigger(Snake(Color.GREEN, 0.1f, 5))
                }
                it.contains("telegram", true) -> {
                    Log.i(TAG, "telegram received")
                    trigger(Kitt(Color.CYAN, 0.1f, 5, 2))
                }
            }
        }
    }

}
