package com.example.kmpweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpweather.data.CurrentWeather
import com.example.kmpweather.data.OpenMeteoWeatherRepository
import com.example.kmpweather.data.WeatherRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// STEP 2: a ViewModel shared by all platforms (androidx lifecycle is multiplatform).
// Deliberately simple: one hardcoded city and nullable state. The final lab improves this.
class WeatherViewModel(
    private val repository: WeatherRepository = OpenMeteoWeatherRepository(),
) : ViewModel() {

    private val _weather = MutableStateFlow<CurrentWeather?>(null)
    val weather: StateFlow<CurrentWeather?> = _weather.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun loadLisbon() {
        viewModelScope.launch {
            try {
                _error.value = null
                _weather.value = repository.getCurrentWeather(latitude = 38.72, longitude = -9.14)
            } catch (e: CancellationException) {
                throw e // never swallow cancellation
            } catch (e: Exception) {
                _error.value = e.message ?: "Something went wrong"
            }
        }
    }
}
