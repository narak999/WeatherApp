package com.bunnarak.weatherapp.domain.repository

import com.bunnarak.weatherapp.domain.util.Resource
import com.bunnarak.weatherapp.domain.weather.DailyWeatherInfo
import com.bunnarak.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>

    suspend fun getDailyWeatherData(lat: Double, long: Double): Resource<DailyWeatherInfo>
}