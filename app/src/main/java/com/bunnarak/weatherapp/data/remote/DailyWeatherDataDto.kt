package com.bunnarak.weatherapp.data.remote

import com.squareup.moshi.Json

data class DailyWeatherDataDto(
    val time: List<String>,
    @field:Json(name="weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name="temperature_2m_max")
    val temperaturesMax: List<Double>,
    @field:Json(name="temperature_2m_min")
    val temperaturesMin: List<Double>,
    @field:Json(name="sunrise")
    val sunrise: List<String>,
    @field:Json(name="sunset")
    val sunset: List<String>,
    @field:Json(name="precipitation_sum")
    val precipitationsSum: List<Double>,
    @field:Json(name="precipitation_probability_max")
    val precipitationsProbability: List<Int>,
    @field:Json(name="windspeed_10m_max")
    val windspeedsMax: List<Double>,
    @field:Json(name="precipitation_hours")
    val precipitationsHours: List<Int>,
    @field:Json(name="uv_index_max")
    val uvIndexMax: List<Double>
)
