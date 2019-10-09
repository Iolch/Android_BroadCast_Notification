package com.example.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import android.app.PendingIntent



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
    fun getContentReplyIntent(context: Context):PendingIntent?{
        val resultIntent = Intent(context, NotificationDetail::class.java)
        val stackBuilder = TaskStackBuilder.create(context)
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NotificationDetail::class.java)
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent)
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        return resultPendingIntent
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

    fun notificationWithReply(context: Context, text: String, title:String){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel(context)
        }

        val KEY_TEXT_REPLY = "key_text_reply"
        var replyLabel: String = "Reply"
        var remoteInput: RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }


        val action =
            NotificationCompat.Action.Builder(R.drawable.ic_send,"teste", getContentReplyIntent(context))
                .addRemoteInput(remoteInput)
                .build()

        val notificationBuilder =
            NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_favorite)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(ActivityCompat.getColor(context,R.color.colorAccent))
                .setDefaults(Notification.DEFAULT_ALL)
                .addAction(action)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(3,notificationBuilder.build())
    }
}
