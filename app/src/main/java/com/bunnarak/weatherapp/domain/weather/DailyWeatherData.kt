package com.bunnarak.weatherapp.domain.weather

import java.time.LocalDate
import java.time.LocalDateTime

data class DailyWeatherData(
    val time: LocalDate,
    val weatherCode: Int,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val sunrise: String,
    val sunset: String,
    val precipitationSum: Double,
    val precipitationProbability: Int,
    val windspeedMax: Double,
    val precipitationHours: Int,
    val uvIndexMax: Double
)
