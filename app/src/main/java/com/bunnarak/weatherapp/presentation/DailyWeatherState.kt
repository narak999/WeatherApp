package com.bunnarak.weatherapp.presentation

import com.bunnarak.weatherapp.domain.weather.DailyWeatherInfo

data class DailyWeatherState(
    val dailyWeatherInfo: DailyWeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
