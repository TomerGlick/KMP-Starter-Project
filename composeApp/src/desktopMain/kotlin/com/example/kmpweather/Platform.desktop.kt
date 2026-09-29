package com.example.kmpweather

// Desktop runs on the JVM, so any Java API is available here.
actual fun platformName(): String =
    "Desktop: ${System.getProperty("os.name")}, Java ${System.getProperty("java.version")}"
