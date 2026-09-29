package com.example.kmpweather.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

// An interface makes it easy to swap in a fake implementation for tests.
interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather
}

// Open-Meteo is free and needs no API key: https://open-meteo.com
class OpenMeteoWeatherRepository(
    private val client: HttpClient = createHttpClient(),
) : WeatherRepository {

    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather =
        client.get("https://api.open-meteo.com/v1/forecast") {
            parameter("latitude", latitude)
            parameter("longitude", longitude)
            parameter("current", "temperature_2m,wind_speed_10m")
        }.body<ForecastResponse>().current
}
