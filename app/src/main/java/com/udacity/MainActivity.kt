package com.udacity

import android.app.DownloadManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.udacity.util.sendNotification
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.udacity.util.NOTIFICATION_ID


class MainActivity : AppCompatActivity() {

    private var downloadID: Long = 0

    private lateinit var notificationManager: NotificationManager
    private lateinit var pendingIntent: PendingIntent
    private lateinit var action: NotificationCompat.Action
    private lateinit var downloadOption: String
    private lateinit var status: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        registerReceiver(receiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        val radioGroup = findViewById<RadioGroup>(R.id.radio_option)

        custom_button.setOnClickListener {

            if (radioGroup.checkedRadioButtonId < 0) {
                Toast.makeText(baseContext, "Please select the file to download", Toast.LENGTH_LONG).show()
            } else {

                download()
                val optionChecked = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                downloadOption = optionChecked.text.toString()
            }
        }

        createChannel(CHANNEL_ID, getString(R.string.notification_title))
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)

            val contentIntent = Intent(context, DetailActivity::class.java)

            status = when (downloadID) {
                id -> "Success"
                else -> "Failed"
            }

            contentIntent.putExtra("status", status)
            contentIntent.putExtra("filename", downloadOption)

            pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_ID,
                contentIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)

            val notificationManager = ContextCompat.getSystemService(
                applicationContext, NotificationManager::class.java
            ) as NotificationManager

            notificationManager.cancelAll()
            custom_button.buttonState = ButtonState.Completed

            notificationManager.sendNotification(R.string.notification_title.toString(), applicationContext, pendingIntent)
        }
    }

    private fun createChannel(channelId: String, channelName: String) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.WHITE
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_description)

            notificationManager = this.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun download() {
        custom_button.buttonState = ButtonState.Loading
        val request =
            DownloadManager.Request(Uri.parse(URL))
                .setTitle(getString(R.string.app_name))
                .setDescription(getString(R.string.app_description))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)

        val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadID =
            downloadManager.enqueue(request)// enqueue puts the download request in the queue.
    }

    companion object Channel {
        private const val URL =
            "https://github.com/udacity/nd940-c3-advanced-android-programming-project-starter"
        const val CHANNEL_ID = "0"
    }

}
