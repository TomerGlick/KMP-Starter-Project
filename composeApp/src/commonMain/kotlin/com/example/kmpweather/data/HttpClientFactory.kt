package com.example.kmpweather.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

// No engine is passed here. Ktor picks the engine that each platform's source set depends on
// (OkHttp on Android, CIO on Desktop, Darwin on iOS), so we don't even need expect/actual.
fun createHttpClient(): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}
