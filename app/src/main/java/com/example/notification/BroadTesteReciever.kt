package com.example.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BroadTesteReciever: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        var on:Boolean = intent!!.getBooleanExtra("state",false)

        if(on){

//            Toast.makeText(context,"Modo Avião Ligado!", Toast.LENGTH_LONG).show()
            NotificationUtils.notificationSimple(context,"Modo Avião Ligado!", "Notificacao")

        }else{

//            Toast.makeText(context,"Modo Avião Desligado!", Toast.LENGTH_LONG).show()
            NotificationUtils.notificationSimple(context,"Modo Avião Desligado!", "Notificacao")
        }
    }
}