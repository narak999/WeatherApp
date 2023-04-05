package com.bunnarak.weatherapp.data.repository

import com.bunnarak.weatherapp.data.mapper.toDailyWeatherInfo
import com.bunnarak.weatherapp.data.mapper.toWeatherInfo
import com.bunnarak.weatherapp.data.remote.WeatherApi
import com.bunnarak.weatherapp.domain.repository.WeatherRepository
import com.bunnarak.weatherapp.domain.util.Resource
import com.bunnarak.weatherapp.domain.weather.DailyWeatherInfo
import com.bunnarak.weatherapp.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long,
                    timezone = "auto"
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occured!")
        }
    }

    override suspend fun getDailyWeatherData(lat: Double, long: Double): Resource<DailyWeatherInfo> {
        return try {
            Resource.Success(
                data = api.getDailyWeatherData(
                    lat = lat,
                    long = long,
                    timezone = "auto"
                ).toDailyWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occured!")
        }
    }
}