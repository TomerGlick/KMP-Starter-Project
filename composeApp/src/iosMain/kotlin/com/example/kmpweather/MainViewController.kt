package com.example.kmpweather

import androidx.compose.ui.window.ComposeUIViewController

// iOS entry point: the Xcode project (iosApp) calls this to get a UIViewController.
fun MainViewController() = ComposeUIViewController { App() }
