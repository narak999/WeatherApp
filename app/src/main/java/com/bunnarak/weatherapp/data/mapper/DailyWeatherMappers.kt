package com.bunnarak.weatherapp.data.mapper

import com.bunnarak.weatherapp.data.remote.DailyWeatherDataDto
import com.bunnarak.weatherapp.data.remote.DailyWeatherDto
import com.bunnarak.weatherapp.domain.datetime.getDateTime
import com.bunnarak.weatherapp.domain.weather.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class DailyIndexedWeatherData(
    val index: Int,
    val data: DailyWeatherData
)

fun DailyWeatherDataDto.toDailyWeatherData(): Map<Int, List<DailyWeatherData>> {
    return time.mapIndexed { index, time ->
        val temperatureMax = temperaturesMax[index]
        val temperatureMin = temperaturesMin[index]
        val sunrise = sunrise[index]
        val sunset = sunset[index]
        val precipitationSum = precipitationsSum[index]
        val precipitationProb = precipitationsProbability[index]
        val windspeed = windspeedsMax[index]
        val precipitationHours = precipitationsHours[index]
        val uvIndex = uvIndexMax[index]
        DailyIndexedWeatherData(
            index = index,
            data = DailyWeatherData(
                time = LocalDate.parse(time, DateTimeFormatter.ISO_LOCAL_DATE),
                temperatureMax = temperatureMax,
                temperatureMin = temperatureMin,
                sunrise = sunrise,
                sunset =  sunset,
                precipitationSum = precipitationSum,
                precipitationProbability = precipitationProb,
                windspeedMax = windspeed,
                precipitationHours = precipitationHours,
                uvIndexMax = uvIndex
            )
        )
    }.groupBy {
        it.index
    }.mapValues { it ->
        it.value.map {it.data}
    }
        //.also { println(it[0]) }
}

fun DailyWeatherDto.toDailyWeatherInfo(): DailyWeatherInfo {
    val weatherDataMap = dailyWeatherData.toDailyWeatherData()
    val now = getDateTime()
    //val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        it.time.dayOfMonth == now.dayOfMonth
    }
    //println(currentWeatherData)
    return DailyWeatherInfo(
        dailyWeatherDataPerDay = weatherDataMap,
        dailyCurrentWeatherData = currentWeatherData
    )
}


