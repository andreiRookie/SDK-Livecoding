package com.andreirookie.sdk_livecoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textField)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this , MyService::class.java)
            startService(intent)
        }

        lifecycleScope.launchWhenStarted {
            MyService.stateFlow.collect {
                textView?.text = it.toString()
            }
        }
    }
}