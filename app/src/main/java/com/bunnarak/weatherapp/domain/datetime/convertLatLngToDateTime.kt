package com.bunnarak.weatherapp.domain.datetime

import com.bunnarak.weatherapp.data.mapper.TimezoneMapper
import com.bunnarak.weatherapp.domain.location.getLat
import com.bunnarak.weatherapp.domain.location.getLong
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

var currentDateTime: ZonedDateTime = ZonedDateTime.now()
fun convertLatLngToDateTime(lat: Double, lng: Double): String {
    val timezoneMapper = TimezoneMapper()
    val timezoneID = timezoneMapper.latLngToTimezoneString(lat, lng)
    val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd h:mm a")
    val zonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(timezoneID))
    currentDateTime = zonedDateTime
    return zonedDateTime.format(dateTimeFormatter)
}

fun getDateTime(): ZonedDateTime {
    convertLatLngToDateTime(getLat(), getLong())
    return currentDateTime
}