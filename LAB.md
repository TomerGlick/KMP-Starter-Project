# Final project: Mini Weather

Start from the `step-2-networking` branch. You already have a working API call for one hardcoded city.
Now turn it into a small, real app. Everything you write in this lab goes in **`commonMain`**,
with no platform-specific code at all.

## Core tasks (~25 min)

**1. A list of cities.**
Create a `City` data class (`name`, `latitude`, `longitude`) and a list of 3–5 cities.
Show them in a `LazyColumn`.

<details><summary>Some coordinates</summary>

Lisbon 38.72, -9.14 · London 51.51, -0.13 · New York 40.71, -74.01 · Tokyo 35.68, 139.69 · São Paulo -23.55, -46.63
</details>

**2. Proper UI state.**
Replace the nullable `StateFlow<CurrentWeather?>` in the ViewModel with a sealed interface:

```kotlin
sealed interface WeatherUiState {
    data object Idle : WeatherUiState
    data class Loading(val city: City) : WeatherUiState
    data class Success(val city: City, val weather: CurrentWeather) : WeatherUiState
    data class Error(val city: City, val message: String) : WeatherUiState
}
```

**3. Tap a city to load its weather.**
Add `selectCity(city: City)` to the ViewModel. It should emit `Loading`, call the repository,
then emit `Success` or `Error`. Show a spinner while loading and a Retry button on error.

<details><summary>Hint: tapping quickly on several cities</summary>

Keep the `Job` returned by `viewModelScope.launch` and cancel it before starting a new load,
so a slow old request can't overwrite a newer one.
</details>

**4. Show the platform.**
Put `platformName()` from step 1 in a footer at the bottom of the screen.

Run it on Android **and** Desktop. Same code, two apps.

## Stretch goals

- **Test it.** In `commonTest`, write a `FakeWeatherRepository` and test that `selectCity` ends in
  `Success` (and in `Error` when the fake throws). Run with `./gradlew :composeApp:desktopTest`.
  Tip: use `Dispatchers.setMain(UnconfinedTestDispatcher())` so `viewModelScope` works in tests.
- **Show more data.** Add `wind_speed_10m` or `relative_humidity_2m` to the request and the model.
- **Remember the last city** between launches, using the
  [multiplatform-settings](https://github.com/russhwolf/multiplatform-settings) library.
- **Run it on iOS** (see the README).

The complete solution is on the `final-solution` branch.
