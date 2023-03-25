package com.bunnarak.weatherapp.domain.location

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.*
import java.io.IOException
import java.util.Locale

var latitude: Double = 0.0
var longitude: Double = 0.0
var location: String = ""
@Composable
fun GetCityAndCountry (
    context: Context,
    lat: Double,
    long: Double
) {
    var address by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = context, key2 = lat, key3 = long) {
        isLoading = true
        address = try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, long, 1)
            val city = addresses[0].locality ?: ""
            val country = addresses[0].countryCode ?: ""
            "$city, $country"
        } catch (e: IOException) {
            "Error: ${e.message}"
        }
        isLoading = false
    }

    if (isLoading) {
        location = "Loading Address"
    }
    location = address
}

fun getLocationCity(): String {
    return location
}

fun setLat(lat: Double){
    latitude = lat
}

fun setLong(long: Double) {
    longitude = long
}

fun getLat(): Double {
    return latitude
}

fun getLong(): Double {
    return longitude
}