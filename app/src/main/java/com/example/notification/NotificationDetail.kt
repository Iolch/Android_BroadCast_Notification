package com.example.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification_detail.*

class NotificationDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)
        txtMessage.text = intent.getStringExtra(EXTRA_MESSAGE)
    }
    companion object{
        val EXTRA_MESSAGE = "message"
    }
}
