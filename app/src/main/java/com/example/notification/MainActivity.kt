package com.example.notification

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var receiver: BroadTesteReciever? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //INI BROADCAST
        receiver = BroadTesteReciever()
        var intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(receiver,intentFilter)
        //FIM BROADCAST

        btnSimples.setOnClickListener {
            NotificationUtils.notificationSimple(this, "oi?", "meu title")
        }
        btnAction.setOnClickListener {
            NotificationUtils.notificationWithAction(this)
        }
    }
}
