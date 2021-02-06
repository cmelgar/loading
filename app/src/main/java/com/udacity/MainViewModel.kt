package com.udacity

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.udacity.util.sendNotification

class MainViewModel (private val app: Application) : AndroidViewModel(app) {

    private val _filename = MutableLiveData<String>()
    val filename: LiveData<String>
        get() = _filename

    init {
//        val notificationManager = ContextCompat.getSystemService(
//                app,
//                NotificationManager::class.java
//        ) as NotificationManager
//        notificationManager.sendNotification(app.getString(R.string.notification_description), app)
    }
}