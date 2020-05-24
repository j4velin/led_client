package de.j4velin.ledclient

import android.graphics.Color
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import de.j4velin.ledclient.lib.*

class NotificationListener : NotificationListenerService() {

    private val controller = LedController("http://192.168.178.23:5000")

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        // TODO read from database
        Log.i(TAG, "notification posted: $sbn")
        sbn?.packageName?.let {
            when {
                it.equals("com.google.android.gm", true) -> {
                    Log.i(TAG, "gmail received")
                    controller.trigger(Flash(Color.RED, 1f, 3))
                }
                it.contains("whatsapp", true) -> {
                    Log.i(TAG, "whatsapp received")
                    controller.trigger(Snake(Color.GREEN, 0.1f, 5))
                }
                it.contains("telegram", true) -> {
                    Log.i(TAG, "telegram received")
                    controller.trigger(Kitt(Color.CYAN, 0.1f, 5, 2))
                }
            }
        }
    }

}
