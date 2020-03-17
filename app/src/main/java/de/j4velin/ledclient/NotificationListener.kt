package de.j4velin.ledclient

import android.graphics.Color
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class NotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        // TODO read from database
        sbn?.packageName?.contains("whatsapp", true).let {
            trigger(Flash(Color.GREEN, 100, 5))
        }
        sbn?.packageName?.contains("telegram", true).let {
            trigger(Flash(Color.CYAN, 100, 5))
        }
        sbn?.packageName?.contains("gmail", true).let {
            trigger(Flash(Color.RED, 100, 5))
        }
    }

}
