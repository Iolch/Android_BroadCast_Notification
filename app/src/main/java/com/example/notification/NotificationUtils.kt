package com.example.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationUtils {
    val CHANNEL_ID = "padrao"
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "Padrão"
        val channelDescription = "Canal padrão de Notificações"

        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = channelDescription
            enableLights(true)
            lightColor = Color.GREEN
            vibrationPattern =
                longArrayOf(100,200,300,400,500,400,300,200,400)
        }
        notificationManager.createNotificationChannel(channel)
    }
    fun getContentIntent(context: Context):PendingIntent?{
        val intent = Intent(context, NotificationDetail::class.java).apply {
            putExtra(NotificationDetail.EXTRA_MESSAGE, "Via notificação")
        }

        return PendingIntent.getActivity(context,0,intent,0)
    }
    fun notificationSimple(context: Context, text: String, title:String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }

        val notificationBuilder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_favorite)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(ActivityCompat.getColor(context,R.color.colorAccent))
                .setDefaults(Notification.DEFAULT_ALL)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1,notificationBuilder.build())
    }

    fun notificationWithAction(context: Context){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }

        val notificationBuilder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_favorite)
                .setContentTitle("Minha Notificação Action")
                .setContentText("Essa é minha notificação Action")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(ActivityCompat.getColor(context,R.color.colorAccent))
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(getContentIntent(context))
                .setAutoCancel(true)

        val notificationManager =
            NotificationManagerCompat.from(context)
        notificationManager.notify(2,notificationBuilder.build())
    }
}