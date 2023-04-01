package com.bunnarak.weatherapp.data.remote

import com.squareup.moshi.Json

data class DailyWeatherDto(

    @field:Json(name = "daily")
    val dailyWeatherData: DailyWeatherDataDto
)
