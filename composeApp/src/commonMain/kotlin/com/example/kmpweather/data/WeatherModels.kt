package com.example.kmpweather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Mirrors the parts of the Open-Meteo response we care about. Example:
// { "current": { "time": "2026-09-29T10:00", "temperature_2m": 21.4, "wind_speed_10m": 12.3 }, ... }
@Serializable
data class ForecastResponse(
    val current: CurrentWeather,
)

@Serializable
data class CurrentWeather(
    val time: String,
    @SerialName("temperature_2m") val temperature: Double,
    @SerialName("wind_speed_10m") val windSpeed: Double,
)
