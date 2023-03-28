package com.bunnarak.weatherapp.presentation

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bunnarak.weatherapp.domain.location.*
import com.bunnarak.weatherapp.domain.repository.WeatherRepository
import com.bunnarak.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker,
): ViewModel() {
    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        var latitude: Double
        var longitude: Double
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                if (getLocation()) {
                    latitude = getLat()
                    longitude = getLong()
                } else {
                    latitude = location.latitude
                    longitude = location.longitude
                    setLat(location.latitude)
                    setLong(location.longitude)
                }
                val result = withContext(Dispatchers.IO) {
                    Log.d(TAG, "loadWeatherInfo: ${Thread.currentThread().name}")
                    repository.getWeatherData(latitude, longitude)
                }
                when (result) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Could not retrieve location. Please allow location access to continue!"
                )
            }
        }
    }
}