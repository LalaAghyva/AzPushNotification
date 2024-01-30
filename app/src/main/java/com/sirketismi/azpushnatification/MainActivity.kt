package com.sirketismi.azpushnatification

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import android.Manifest
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askNotificationPermission()
    }

    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {granted ->
        if(granted) {
            getToken()
        } else {

        }
    }

    fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {task ->
            if(task.isSuccessful) {
                println(task.result)
            }
        }
    }
}

//c1gagk0WSEqLs3CyVkS3zr:APA91bH6reLli6u2lSLibomWoavnTq2nS8h8sqLCB8acVmRXCoqK1XtKgPP-lUtnJ8_zXK_gVVMnA91_AyY_oBgp4TRTDyc_6eDJVvvC8u0yDyQRQ17jAPtlgxjYI0X2GuDBkFI4uAsu