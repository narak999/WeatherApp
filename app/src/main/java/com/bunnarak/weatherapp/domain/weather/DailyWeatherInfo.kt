package com.bunnarak.weatherapp.domain.weather

data class DailyWeatherInfo(
    val dailyWeatherDataPerDay: Map<Int, List<DailyWeatherData>>,
    val dailyCurrentWeatherData: DailyWeatherData?
)
