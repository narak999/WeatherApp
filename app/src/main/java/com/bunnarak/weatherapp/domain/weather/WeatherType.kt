package com.bunnarak.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.bunnarak.weatherapp.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int,
    @DrawableRes val backgroundImage: Int
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear Sky",
        iconRes = R.drawable.ic_sunny,
        backgroundImage = R.drawable.sunny_background
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly Clear",
        iconRes = R.drawable.ic_cloudy,
        backgroundImage = R.drawable.sunny_background
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly Cloudy",
        iconRes = R.drawable.ic_cloudy,
        backgroundImage = R.drawable.sunny_background
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_cloudy,
        backgroundImage = R.drawable.sunny_background
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy,
        backgroundImage = R.drawable.sunny_background
    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing Rime Fog",
        iconRes = R.drawable.ic_very_cloudy,
        backgroundImage = R.drawable.sunny_background
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light Drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundImage = R.drawable.sunny_background
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate Drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundImage = R.drawable.sunny_background
    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense Drizzle",
        iconRes = R.drawable.ic_rainshower,
        backgroundImage = R.drawable.sunny_background
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight Freezing Drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundImage = R.drawable.sunny_background
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense Freezing Drizzle",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundImage = R.drawable.sunny_background
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight Rain",
        iconRes = R.drawable.ic_rainy,
        backgroundImage = R.drawable.sunny_background
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rainy,
        backgroundImage = R.drawable.sunny_background
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy Rain",
        iconRes = R.drawable.ic_rainy,
        backgroundImage = R.drawable.sunny_background
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy Freezing Rain",
        iconRes = R.drawable.ic_snowyrainy,
        backgroundImage = R.drawable.sunny_background
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight Snowfall",
        iconRes = R.drawable.ic_snowy,
        backgroundImage = R.drawable.snow_background
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate Snowfall",
        iconRes = R.drawable.ic_heavysnow,
        backgroundImage = R.drawable.snow_background
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy Snowfall",
        iconRes = R.drawable.ic_heavysnow,
        backgroundImage = R.drawable.snow_background
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow Grains",
        iconRes = R.drawable.ic_heavysnow,
        backgroundImage = R.drawable.snow_background
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight Rain Showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundImage = R.drawable.snow_background
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate Rain Showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundImage = R.drawable.snow_background
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent Rain Showers",
        iconRes = R.drawable.ic_rainshower,
        backgroundImage = R.drawable.snow_background
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light Snow Showers",
        iconRes = R.drawable.ic_snowy,
        backgroundImage = R.drawable.snow_background
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy Snow Showers",
        iconRes = R.drawable.ic_snowy,
        backgroundImage = R.drawable.snow_background
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate Thunderstorm",
        iconRes = R.drawable.ic_thunder,
        backgroundImage = R.drawable.snow_background
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm With Slight Hail",
        iconRes = R.drawable.ic_rainythunder,
        backgroundImage = R.drawable.snow_background
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm With Heavy Hail",
        iconRes = R.drawable.ic_rainythunder,
        backgroundImage = R.drawable.snow_background
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