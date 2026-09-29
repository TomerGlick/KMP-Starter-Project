package com.example.kmpweather

import platform.UIKit.UIDevice

// Kotlin/Native lets us call iOS frameworks (UIKit here) directly from Kotlin.
actual fun platformName(): String =
    "${UIDevice.currentDevice.systemName()} ${UIDevice.currentDevice.systemVersion}"
