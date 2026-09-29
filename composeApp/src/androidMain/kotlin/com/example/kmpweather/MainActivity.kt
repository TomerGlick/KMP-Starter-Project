package com.example.kmpweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Android entry point: the only Android-specific UI code is this call to App().
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { App() }
    }
}
