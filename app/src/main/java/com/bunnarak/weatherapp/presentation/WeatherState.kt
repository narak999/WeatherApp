package com.bunnarak.weatherapp.presentation

import com.bunnarak.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val cityCountry: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
