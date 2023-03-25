package com.bunnarak.weatherapp.domain.location

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.*
import java.io.IOException
import java.util.Locale

var latitude: Double = 0.0
var longitude: Double = 0.0
@Composable
fun getCityAndCountry (
    context: Context
):String {
    var address by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = context, key2 = latitude, key3 = longitude) {
        isLoading = true
        address = try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            val city = addresses[0].locality ?: ""
            val country = addresses[0].countryCode ?: ""
            "$city, $country"
        } catch (e: IOException) {
            "Error: ${e.message}"
        }
        isLoading = false
    }

    if (isLoading) {
        return "Loading Address"
    }
    return address
}

fun getLat(lat: Double){
    latitude = lat
}

fun getLong(long: Double) {
    longitude = long
}