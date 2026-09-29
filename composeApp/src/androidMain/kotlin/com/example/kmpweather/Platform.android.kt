package com.example.kmpweather

import android.os.Build

// We can use Android APIs here because this file is in androidMain.
actual fun platformName(): String =
    "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
