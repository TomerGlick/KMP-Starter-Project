package com.example.kmpweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// STEP 0: this UI is written once in commonMain and runs on Android, Desktop and iOS.
@Composable
fun App() {
    MaterialTheme {
        var clicks by remember { mutableStateOf(0) }

        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Hello, KMP!", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Text("This screen lives in commonMain.")
            Spacer(Modifier.height(16.dp))
            Button(onClick = { clicks++ }) {
                Text("Clicked $clicks times")
            }
        }
    }
}
