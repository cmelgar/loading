package com.udacity.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import com.udacity.MainActivity
import com.udacity.R

val NOTIFICATION_ID = 0

fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context, pendingIntent: PendingIntent) {

    val builder = NotificationCompat.Builder(
            applicationContext,
            MainActivity.CHANNEL_ID
    )
            .setSmallIcon(R.drawable.ic_assistant_black_24dp)
            .setContentTitle(applicationContext.getString(R.string.notification_title))
            .setContentText(applicationContext.getString(R.string.notification_description))
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_assistant_black_24dp, "", pendingIntent)
            .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}