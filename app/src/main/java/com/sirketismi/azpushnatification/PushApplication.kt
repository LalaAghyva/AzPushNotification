package com.sirketismi.azpushnatification

import android.app.Application
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PushApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.Debug.logLevel = LogLevel.DEBUG
        OneSignal.initWithContext(this,"1e3b4836-5b3a-4090-bd0b-9789e8f7050e")

        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }

    }
}