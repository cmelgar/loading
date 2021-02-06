package com.udacity.receiver

import android.app.DownloadManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.udacity.DetailActivity
import com.udacity.R
import com.udacity.util.NOTIFICATION_ID
import com.udacity.util.sendNotification

class DownloadReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
//        val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
//
//        val contentIntent = Intent(context, DetailActivity::class.java)
//
//        contentIntent.putExtra("id", id)
//        contentIntent.putExtra("url", "avluevalue")
//
//        val pendingIntent = PendingIntent.getActivity(
//            context,
//        NOTIFICATION_ID,
//        contentIntent,
//        PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val notificationManager = ContextCompat.getSystemService(
//            context, NotificationManager::class.java
//        ) as NotificationManager
////        notificationManager.cancelAll()
//        notificationManager.sendNotification(R.string.notification_title.toString(), context, pendingIntent)
    }
}