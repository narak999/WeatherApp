package com.bunnarak.weatherapp.domain.repository

import android.content.Context
import com.bunnarak.weatherapp.domain.util.Resource
import com.bunnarak.weatherapp.domain.weather.WeatherInfo
import dagger.hilt.android.qualifiers.ApplicationContext

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}