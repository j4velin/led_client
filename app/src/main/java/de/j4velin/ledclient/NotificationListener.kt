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
                it.contains("gmail", true) -> {
                    Log.i(TAG, "gmail received")
                    trigger(Flash(Color.RED, 1.5f, 5))
                }
                it.contains("whatsapp", true) -> {
                    Log.i(TAG, "whatsapp received")
                    trigger(Flash(Color.GREEN, 0.5f, 5))
                }
                it.contains("telegram", true) -> {
                    Log.i(TAG, "telegram received")
                    trigger(Flash(Color.CYAN, 1f, 5))
                }
            }
        }
    }

}
