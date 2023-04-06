package com.bunnarak.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.bunnarak.weatherapp.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
    val modifier: Modifier
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear Sky",
        iconRes = R.drawable.ic_sunny,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFfe980d),
                    Color(0xFFfebb39)
                )
            )
        )
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly Clear",
        iconRes = R.drawable.ic_cloudy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF00accf),
                    Color(0xFF00d4fd)
                )
            )
        )
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly Cloudy",
        iconRes = R.drawable.ic_cloudy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF00accf),
                    Color(0xFF00d4fd)
                )
            )
        )
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_cloudy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF79b3d3),
                    Color(0xFF97c8dc)
                )
            )
        )
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF79b3d3),
                    Color(0xFF97c8dc)
                )
            )
        )
    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing Rime Fog",
        iconRes = R.drawable.ic_very_cloudy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF79b3d3),
                    Color(0xFF97c8dc)
                )
            )
        )
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light Drizzle",
        iconRes = R.drawable.ic_rainshower,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF64c5de),
                    Color.White
                )
            )
        )
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate Drizzle",
        iconRes = R.drawable.ic_rainshower,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense Drizzle",
        iconRes = R.drawable.ic_rainshower,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight Freezing Drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1cb6d8),
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense Freezing Drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1cb6d8),
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight Rain",
        iconRes = R.drawable.ic_rainy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rainy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy Rain",
        iconRes = R.drawable.ic_rainy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy Freezing Rain",
        iconRes = R.drawable.ic_snowyrainy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF1cb6d8),
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight Snowfall",
        iconRes = R.drawable.ic_snowy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF93d6ed),
                    Color(0xFF60aed5)
                )
            )
        )
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate Snowfall",
        iconRes = R.drawable.ic_heavysnow,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF93d6ed),
                    Color(0xFF60aed5)
                )
            )
        )
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy Snowfall",
        iconRes = R.drawable.ic_heavysnow,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF93d6ed),
                    Color(0xFF60aed5)
                )
            )
        )
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow Grains",
        iconRes = R.drawable.ic_heavysnow,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF93d6ed),
                    Color(0xFF60aed5)
                )
            )
        )
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight Rain Showers",
        iconRes = R.drawable.ic_rainshower,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate Rain Showers",
        iconRes = R.drawable.ic_rainshower,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent Rain Showers",
        iconRes = R.drawable.ic_rainshower,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF005292),
                    Color(0xFF003c67)
                )
            )
        )
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light Snow Showers",
        iconRes = R.drawable.ic_snowy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF93d6ed),
                    Color(0xFF60aed5)
                )
            )
        )
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy Snow Showers",
        iconRes = R.drawable.ic_snowy,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF93d6ed),
                    Color(0xFF60aed5)
                )
            )
        )
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate Thunderstorm",
        iconRes = R.drawable.ic_thunder,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF857dae),
                    Color(0xFF625c82)
                )
            )
        )
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm With Slight Hail",
        iconRes = R.drawable.ic_rainythunder,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF857dae),
                    Color(0xFF625c82)
                )
            )
        )
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm With Heavy Hail",
        iconRes = R.drawable.ic_rainythunder,
        modifier = Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF857dae),
                    Color(0xFF625c82)
                )
            )
        )
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}