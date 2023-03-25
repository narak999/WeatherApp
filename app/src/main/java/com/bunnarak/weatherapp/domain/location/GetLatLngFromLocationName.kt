package com.bunnarak.weatherapp.domain.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.io.IOException

suspend fun getLatLngFromLocationName(context: Context, locationName: String): Pair<Double, Double>? {
    val geocoder = Geocoder(context)
    try {
        val addresses: List<Address> = geocoder.getFromLocationName(locationName, 1)
        if (addresses.isNotEmpty()) {
            val latitude = addresses[0].latitude
            val longitude = addresses[0].longitude
            return Pair(latitude, longitude)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}