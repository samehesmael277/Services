package com.sameh.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast

class MyService : Service() {

    val SERVICE_ID = 1

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_1"
            val channel = NotificationChannel(channelId, "Default", NotificationManager.IMPORTANCE_DEFAULT)

            val manger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manger.createNotificationChannel(channel)

            val notification = Notification.Builder(this, channelId).apply {
                setContentTitle("Notification title")
                setContentText("this is content")
                setSmallIcon(R.drawable.ic_launcher_foreground)
            }.build()

            startForeground(SERVICE_ID, notification)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showMessage()
        return super.onStartCommand(intent, flags, startId)
    }

    fun showMessage() {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(this, "hello from services", Toast.LENGTH_SHORT).show()
        },
            5000
        )
    }

}