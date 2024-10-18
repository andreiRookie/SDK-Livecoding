package com.andreirookie.sdk_livecoding

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyService : Service() {

    private val scope = CoroutineScope(Dispatchers.Default)

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var progress = 0

        scope.launch {
            while (progress <= 100) {

                delay(150L)

                _stateFlow.value = progress

                progress += 10
            }
        }
        return START_NOT_STICKY
    }

    companion object {
        private val _stateFlow = MutableStateFlow<Int>(0)
        val stateFlow: StateFlow<Int> get() = _stateFlow
    }
}