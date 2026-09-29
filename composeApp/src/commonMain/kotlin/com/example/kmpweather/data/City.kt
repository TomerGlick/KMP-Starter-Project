package com.example.kmpweather.data

data class City(
    val name: String,
    val latitude: Double,
    val longitude: Double,
)

val defaultCities = listOf(
    City("Lisbon", 38.72, -9.14),
    City("London", 51.51, -0.13),
    City("New York", 40.71, -74.01),
    City("Tokyo", 35.68, 139.69),
    City("São Paulo", -23.55, -46.63),
)
