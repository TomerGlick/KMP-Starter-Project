package com.example.kmpweather.ui

import com.example.kmpweather.data.City
import com.example.kmpweather.data.CurrentWeather

// One sealed type describes every state the screen can be in, so the UI can't end up
// showing a spinner and an error at the same time.
sealed interface WeatherUiState {
    data object Idle : WeatherUiState
    data class Loading(val city: City) : WeatherUiState
    data class Success(val city: City, val weather: CurrentWeather) : WeatherUiState
    data class Error(val city: City, val message: String) : WeatherUiState
}

val WeatherUiState.selectedCity: City?
    get() = when (this) {
        WeatherUiState.Idle -> null
        is WeatherUiState.Loading -> city
        is WeatherUiState.Success -> city
        is WeatherUiState.Error -> city
    }
