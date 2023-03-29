package com.bunnarak.weatherapp.presentation

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bunnarak.weatherapp.domain.datetime.convertLatLngToDateTime
import com.bunnarak.weatherapp.domain.location.GetCityAndCountry
import com.bunnarak.weatherapp.domain.location.getLat
import com.bunnarak.weatherapp.domain.location.getLocationCity
import com.bunnarak.weatherapp.domain.location.getLong
import kotlin.math.roundToInt

@Composable
fun WeatherCard (
    viewModel: WeatherViewModel,
    backgroundColor: Color,
    context: Context,
    modifier: Modifier = Modifier
) {
    var rotated by remember {
        mutableStateOf(false)
    }
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateColor by animateColorAsState(
        targetValue = if (rotated) Color.Blue else Color.Green,
        animationSpec = tween(500)
    )


    val latitude = getLat()
    val longitude = getLong()
    viewModel.loadWeatherInfo()
    val dateTime = convertLatLngToDateTime(latitude, longitude)
    viewModel.state.weatherInfo?.currentWeatherData?.let { data ->
        GetCityAndCountry(context = context, lat = latitude, long = longitude)
        Card (
            backgroundColor = backgroundColor,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .padding(16.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 8 * density
                }
                .clickable {
                   rotated = !rotated
                },
            elevation = 8.dp
        ) {
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!rotated) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = dateTime,
                            color = Color.White,
                            style = MaterialTheme.typography.body1
                        )
                        Text(
                            text = getLocationCity(),
                            color = Color.White,
                            style = MaterialTheme.typography.body1
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Image(
                        painter = painterResource(id = data.weatherType.iconRes),
                        contentDescription = null,
                        modifier = Modifier.width(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${data.temperatureCelsius.toInt()}°C",
                        fontSize = 50.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = data.weatherType.weatherDesc,
                        fontSize = 20.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherIconDisplay(
                            value = data.pressure.roundToInt(),
                            unit = " hpa",
                            icon = ImageVector.vectorResource(id = com.bunnarak.weatherapp.R.drawable.ic_pressure),
                            iconTint = Color.White,
                            textStyle = MaterialTheme.typography.body1
                        )
                        WeatherIconDisplay(
                            value = data.humidity.roundToInt(),
                            unit = "%",
                            icon = ImageVector.vectorResource(id = com.bunnarak.weatherapp.R.drawable.ic_drop),
                            iconTint = Color.White,
                            textStyle = MaterialTheme.typography.body1
                        )
                        WeatherIconDisplay(
                            value = data.windSpeed.roundToInt(),
                            unit = "km/h",
                            icon = ImageVector.vectorResource(id = com.bunnarak.weatherapp.R.drawable.ic_wind),
                            iconTint = Color.White,
                            textStyle = MaterialTheme.typography.body1
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = dateTime,
                            color = Color.White,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .graphicsLayer {
                                    rotationY = rotation
                                }
                        )
                    }
                }
            }
        }
    }
}