package com.example.kmpweather.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kmpweather.data.City

// Stateless screen: it only receives state and reports events. This makes it easy to preview
// and keeps all logic in the ViewModel.
@Composable
fun WeatherScreen(
    state: WeatherUiState,
    cities: List<City>,
    platform: String,
    onCitySelected: (City) -> Unit,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().safeContentPadding().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text("Mini Weather", style = MaterialTheme.typography.headlineMedium)

        WeatherPanel(state = state, onRetry = onRetry)

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(cities, key = { it.name }) { city ->
                CityRow(
                    city = city,
                    isSelected = city == state.selectedCity,
                    onClick = { onCitySelected(city) },
                )
            }
        }

        Text(
            text = "Running on $platform",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
private fun WeatherPanel(state: WeatherUiState, onRetry: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(16.dp).heightIn(min = 88.dp),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                WeatherUiState.Idle ->
                    Text("Pick a city to see its current weather")

                is WeatherUiState.Loading ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(Modifier.height(8.dp))
                        Text("Loading ${state.city.name}…")
                    }

                is WeatherUiState.Success ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(state.city.name, style = MaterialTheme.typography.titleMedium)
                        Text(
                            "${state.weather.temperature} °C",
                            style = MaterialTheme.typography.displaySmall,
                        )
                        Text("Wind ${state.weather.windSpeed} km/h")
                    }

                is WeatherUiState.Error ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "Couldn't load ${state.city.name}: ${state.message}",
                            color = MaterialTheme.colorScheme.error,
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = onRetry) { Text("Retry") }
                    }
            }
        }
    }
}

@Composable
private fun CityRow(city: City, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        colors = if (isSelected) {
            CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        } else {
            CardDefaults.cardColors()
        },
    ) {
        Text(
            text = city.name,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
        )
    }
}
