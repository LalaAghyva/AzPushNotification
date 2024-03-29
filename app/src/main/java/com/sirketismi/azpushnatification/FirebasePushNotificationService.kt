package com.sirketismi.azpushnatification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.Date


class FirebasePushNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val taskStackBuilder : TaskStackBuilder = TaskStackBuilder.create(this)
        taskStackBuilder.addParentStack(MainActivity::class.java)
        taskStackBuilder.addNextIntent(resultIntent)

        var requestCode = 1001
        val pendingIntent : PendingIntent = taskStackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_IMMUTABLE)

        val channelId = "tanitim"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText("Bildirim detayı")
            .setContentTitle("Bildirim başlığı")
            .setAutoCancel(false)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Tanıtım Bildirimleri",
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val id = Date().time.toInt()
        notificationManager.notify(id, notificationBuilder.build())
    }


    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    //apini guncelle
}

/* {
 "message": { "token":"c1gagk0WSEqLs3CyVkS3zr:APA91bH6reLli6u2lSLibomWoavnTq2nS8h8sqLCB8acVmRXCoqK1XtKgPPlUtnJ8_zXK_gVVMnA91_AyY_oBgp4TRTDyc_6eDJVvvC8u0yDyQRQ17jAPtlgxjYI0X2GuDBkFI4uAsu",
  "notification": {
   "body": "Body of Your Notification in data",
   "title": "Title of Your Notification in data"
   },
  "data": {
   "body": "Body of Your Notification in data",
   "title": "Title of Your Notification in data",
   "key_1": "Value for key_1",
   "key_2": "Value for key_2",
   "siparisID": "123"
   }
  }
}

*/