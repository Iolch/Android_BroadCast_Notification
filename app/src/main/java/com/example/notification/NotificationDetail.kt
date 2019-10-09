package com.example.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification_detail.*
import android.content.Intent
import androidx.core.app.RemoteInput


class NotificationDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_detail)
        txtMessage.text = intent.getStringExtra(EXTRA_MESSAGE)
        txtMessage.text  = getMessageText(intent);
    }
    private fun getMessageText(intent: Intent): CharSequence? {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        return if (remoteInput != null) {
            remoteInput!!.getCharSequence("key_text_reply")
        } else null
    }

    companion object{
        val EXTRA_MESSAGE = "message"
    }
}
