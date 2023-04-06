package com.bunnarak.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bunnarak.weatherapp.domain.weather.WeatherType
import java.time.DayOfWeek
import kotlin.math.roundToInt

@Composable
fun DailyForecast(
    state: DailyWeatherState,
    modifier: Modifier = Modifier
) {
    var flag = false
    state.dailyWeatherInfo?.dailyWeatherDataPerDay?.let { data ->
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
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "7-Day Forecast",
                    fontSize = 20.sp,
                    color = Color.White
                )
                for (value in data.values) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = Color.White, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (!flag) "Today" else getThreeDigitDayOfWeek(value[0].time.dayOfWeek),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Image(
                            painterResource(id = WeatherType.fromWMO(value[0].weatherCode).iconRes),
                            contentDescription = null,
                            modifier = Modifier.width(40.dp)
                        )
                        Text(
                            text = "${value[0].temperatureMin.roundToInt()}°C",
                            color = Color.LightGray,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "${value[0].temperatureMax.roundToInt()}°C",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                        flag = true
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

fun getThreeDigitDayOfWeek(dayOfWeek: DayOfWeek): String {
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Mon"
        DayOfWeek.TUESDAY -> "Tue"
        DayOfWeek.WEDNESDAY -> "Wed"
        DayOfWeek.THURSDAY -> "Thu"
        DayOfWeek.FRIDAY -> "Fri"
        DayOfWeek.SATURDAY -> "Sat"
        DayOfWeek.SUNDAY -> "Sun"
    }
}