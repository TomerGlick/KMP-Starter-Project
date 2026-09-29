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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpweather.ui.WeatherViewModel

// STEP 2: the UI observes the shared ViewModel's StateFlows.
@Composable
fun App() {
    MaterialTheme {
        // On non-Android platforms there's no reflection, so we tell viewModel() how to create it.
        val viewModel = viewModel { WeatherViewModel() }
        val weather by viewModel.weather.collectAsState()
        val error by viewModel.error.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Lisbon", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))

            val current = weather
            when {
                error != null -> Text("Error: $error", color = MaterialTheme.colorScheme.error)
                current != null -> Text(
                    "${current.temperature} °C, wind ${current.windSpeed} km/h",
                    style = MaterialTheme.typography.titleLarge,
                )
                else -> Text("Tap the button to load the weather")
            }

            Spacer(Modifier.height(16.dp))
            Button(onClick = viewModel::loadLisbon) { Text("Load weather") }
            Spacer(Modifier.height(24.dp))
            Text("Running on ${platformName()}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
