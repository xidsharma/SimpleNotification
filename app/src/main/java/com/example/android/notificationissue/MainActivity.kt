package com.example.android.notificationissue

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendNotification(view: View) {
        val titleText = getString(R.string.app_name)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            titleText,
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationManager.createNotificationChannel(channel)

        val launchActivityIntent = Intent(applicationContext, MainActivity::class.java)
        launchActivityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val notificationText = "Test notification"

        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(PendingIntent.getActivity(this, 0, launchActivityIntent, 0))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentText(notificationText)
            .setContentTitle(titleText)
            .setColor(ContextCompat.getColor(this, R.color.colorAccent))
            .setStyle(NotificationCompat.BigTextStyle().bigText(notificationText))
            .build()

        notificationManager.notify(NOTIFICATION_TAG, 0, notification)
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "${BuildConfig.APPLICATION_ID}.id.NOTIFICATION_CHANNEL"
        private const val NOTIFICATION_TAG = "${BuildConfig.APPLICATION_ID}.id.TEST_NOTIFICATION"
    }
}