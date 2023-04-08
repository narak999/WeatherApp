package com.bunnarak.weatherapp.presentation

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bunnarak.weatherapp.R
import com.bunnarak.weatherapp.domain.datetime.convertLatLngToDateTime
import com.bunnarak.weatherapp.domain.location.GetCityAndCountry
import com.bunnarak.weatherapp.domain.location.getLat
import com.bunnarak.weatherapp.domain.location.getLocationCity
import com.bunnarak.weatherapp.domain.location.getLong
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard (
    viewModel: WeatherViewModel,
    backgroundColor: Color,
    context: Context
) {
    viewModel.loadWeatherInfo()
    viewModel.loadDailyWeatherInfo()
    var rotated by remember {
        mutableStateOf(false)
    }
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(300)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(300)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(300)
    )

    val latitude = getLat()
    val longitude = getLong()

    val dateTime = convertLatLngToDateTime(latitude, longitude)
    GetCityAndCountry(context = context, lat = latitude, long = longitude)
        Card (
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(16.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 5 * density
                }
                .clickable {
                    rotated = !rotated
                },
            elevation = 8.dp
        ) {
            if (!rotated) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    viewModel.state.weatherInfo?.currentWeatherData?.let { data ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = dateTime,
                                color = Color.White,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.graphicsLayer { alpha = animateFront }
                            )
                            Text(
                                text = getLocationCity(),
                                color = Color.White,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.graphicsLayer { alpha = animateFront }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(
                                id = data.weatherType.iconRes
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(200.dp)
                                .graphicsLayer { alpha = animateFront }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${data.temperatureCelsius.roundToInt()}°C",
                            fontSize = 50.sp,
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.graphicsLayer { alpha = animateFront }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = data.weatherType.weatherDesc,
                            fontSize = 25.sp,
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.graphicsLayer { alpha = animateFront }
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            WeatherIconDisplay(
                                value = data.pressure.roundToInt(),
                                unit = " hpa",
                                icon = ImageVector.vectorResource(id=R.drawable.ic_pressure),
                                iconTint = Color.White,
                                textStyle = MaterialTheme.typography.body1,
                                modifier = Modifier.graphicsLayer { alpha = animateFront }
                            )
                            WeatherIconDisplay(
                                value = data.humidity.roundToInt(),
                                unit = "%",
                                icon = ImageVector.vectorResource(id=R.drawable.ic_drop),
                                iconTint = Color.White,
                                textStyle = MaterialTheme.typography.body1,
                                modifier = Modifier.graphicsLayer { alpha = animateFront }
                            )
                            WeatherIconDisplay(
                                value = data.windSpeed.roundToInt(),
                                unit = "km/h",
                                icon = ImageVector.vectorResource(id=R.drawable.ic_wind),
                                iconTint = Color.White,
                                textStyle = MaterialTheme.typography.body1,
                                modifier = Modifier.graphicsLayer { alpha = animateFront }
                            )
                        }
                    }
                }
            }
        else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                viewModel.state2.dailyWeatherInfo?.dailyCurrentWeatherData?.let { data ->
                    val sunriseDT = LocalDateTime.parse(data.sunrise, DateTimeFormatter.ISO_DATE_TIME).format(
                        DateTimeFormatter.ofPattern("h:mm a"))
                    val sunsetDT = LocalDateTime.parse(data.sunset, DateTimeFormatter.ISO_DATE_TIME).format(
                        DateTimeFormatter.ofPattern("h:mm a"))

                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = getLocationCity(),
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.graphicsLayer {
                                alpha = animateBack
                                rotationY = rotation
                            }
                        )
                        Text(
                            text = dateTime,
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.graphicsLayer {
                                alpha = animateBack
                                rotationY = rotation
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.thermometer_up_742_svgrepo_com),
                        value = "Max Temp: ${data.temperatureMax}°C",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.thermometer_down_741_svgrepo_com),
                        value = "Min Temp: ${data.temperatureMin}°C",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.sunrise_up_svgrepo_com),
                        value = "Sunrise: $sunriseDT",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.sunset_down_svgrepo_com),
                        value = "Sunset: $sunsetDT",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.hot_temperature_svgrepo_com),
                        value = "UV Index: ${data.uvIndexMax}",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.rain_gauge_svgrepo_com),
                        value = "Total Precipitation: ${data.precipitationSum}mm",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.rain_svgrepo_com),
                        value = "Precipitation Hours: ${data.precipitationHours}h",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.percent_symbol_svgrepo_com),
                        value = "Precipitation Prob: ${data.precipitationProbability}%",
                        rotation = rotation,
                        animateBack = animateBack
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DailyWeatherDetailText(
                        icon = ImageVector.vectorResource(id = R.drawable.wind_svgrepo_com),
                        value = "Max Wind Speed: ${data.windspeedMax}km/h",
                        rotation = rotation,
                        animateBack = animateBack)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}