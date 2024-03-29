package com.bunnarak.weatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Card(
            backgroundColor = Color(0xFF009DFF),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Today",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(content = {
                    items(data) {weatherData ->
                        HourlyWeatherDisplay(
                            weatherData = weatherData,
                            modifier = Modifier
                                .height(100.dp)
                                .padding(horizontal = 16.dp)
                        )
                    }
                })
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}