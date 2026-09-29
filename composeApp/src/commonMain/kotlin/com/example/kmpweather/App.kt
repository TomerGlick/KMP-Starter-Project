package com.example.kmpweather

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kmpweather.ui.WeatherScreen
import com.example.kmpweather.ui.WeatherViewModel

@Composable
fun App() {
    MaterialTheme {
        val viewModel = viewModel { WeatherViewModel() }
        val state by viewModel.uiState.collectAsState()

        WeatherScreen(
            state = state,
            cities = viewModel.cities,
            platform = platformName(),
            onCitySelected = viewModel::selectCity,
            onRetry = viewModel::retry,
        )
    }
}
