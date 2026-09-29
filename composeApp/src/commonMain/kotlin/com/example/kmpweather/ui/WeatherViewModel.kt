package com.example.kmpweather.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmpweather.data.City
import com.example.kmpweather.data.OpenMeteoWeatherRepository
import com.example.kmpweather.data.WeatherRepository
import com.example.kmpweather.data.defaultCities
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository = OpenMeteoWeatherRepository(),
    val cities: List<City> = defaultCities,
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    fun selectCity(city: City) {
        // Cancel any request still in flight so a slow old response can't overwrite a newer one.
        loadJob?.cancel()
        _uiState.value = WeatherUiState.Loading(city)

        loadJob = viewModelScope.launch {
            _uiState.value = try {
                val weather = repository.getCurrentWeather(city.latitude, city.longitude)
                WeatherUiState.Success(city, weather)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                WeatherUiState.Error(city, e.message ?: "Something went wrong")
            }
        }
    }

    fun retry() {
        (uiState.value as? WeatherUiState.Error)?.let { selectCity(it.city) }
    }
}
