package com.bunnarak.weatherapp.domain.datetime

import com.bunnarak.weatherapp.data.mapper.TimezoneMapper
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun convertLatLngToDateTime(lat: Double, lng: Double): String {
    val timezoneMapper = TimezoneMapper()
    val timezoneID = timezoneMapper.latLngToTimezoneString(lat, lng)
    val dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd h:mm a")
    val zonedDateTime = ZonedDateTime.now()
    val updatedZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of(timezoneID)).format(dateTimeFormatter)
    return updatedZonedDateTime
}