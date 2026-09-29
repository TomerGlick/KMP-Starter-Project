package com.example.kmpweather

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

// Desktop entry point: opens a window and shows the same shared App().
fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "KMP Weather") {
        App()
    }
}
