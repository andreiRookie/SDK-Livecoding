package com.andreirookie.sdk_livecoding

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val data = p1?.getIntExtra(PROGRESS_DATA, 0)
            textView?.text = "${data.toString()}%"
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textField)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this , MyService::class.java)
            startService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(receiver, IntentFilter(BROADCAST_ACTION), RECEIVER_NOT_EXPORTED)
    }

    override fun onStop() {
        unregisterReceiver(receiver)
        super.onStop()
    }

//    override fun onNewIntent(intent: Intent?) {
////        val data = intent?.getIntExtra(PROGRESS_DATA, 0)
////        textView?.text = data.toString()
//        super.onNewIntent(intent)
//    }

    companion object {
        const val BROADCAST_ACTION = "action"
        const val PROGRESS_DATA = "data"
    }
}