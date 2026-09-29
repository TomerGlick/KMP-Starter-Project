package com.example.kmpweather

import com.example.kmpweather.data.CurrentWeather
import com.example.kmpweather.data.WeatherRepository
import com.example.kmpweather.ui.WeatherUiState
import com.example.kmpweather.ui.WeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

// This test lives in commonTest, so it runs on every platform (e.g. ./gradlew :composeApp:desktopTest).
private class FakeWeatherRepository(private val result: Result<CurrentWeather>) : WeatherRepository {
    override suspend fun getCurrentWeather(latitude: Double, longitude: Double): CurrentWeather =
        result.getOrThrow()
}

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private val sampleWeather = CurrentWeather(time = "2026-09-29T10:00", temperature = 21.5, windSpeed = 12.0)

    // viewModelScope runs on Dispatchers.Main, which doesn't exist in unit tests, so we replace it.
    @BeforeTest
    fun setUp() = Dispatchers.setMain(UnconfinedTestDispatcher())

    @AfterTest
    fun tearDown() = Dispatchers.resetMain()

    @Test
    fun startsIdle() {
        val viewModel = WeatherViewModel(FakeWeatherRepository(Result.success(sampleWeather)))
        assertEquals(WeatherUiState.Idle, viewModel.uiState.value)
    }

    @Test
    fun selectingCityEndsInSuccess() {
        val viewModel = WeatherViewModel(FakeWeatherRepository(Result.success(sampleWeather)))
        val city = viewModel.cities.first()

        viewModel.selectCity(city)

        assertEquals(WeatherUiState.Success(city, sampleWeather), viewModel.uiState.value)
    }

    @Test
    fun failureEndsInError() {
        val viewModel = WeatherViewModel(FakeWeatherRepository(Result.failure(Exception("offline"))))
        val city = viewModel.cities.first()

        viewModel.selectCity(city)

        val state = assertIs<WeatherUiState.Error>(viewModel.uiState.value)
        assertEquals("offline", state.message)
    }
}
