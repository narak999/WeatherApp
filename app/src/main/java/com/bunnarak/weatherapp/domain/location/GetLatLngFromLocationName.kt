package com.bunnarak.weatherapp.domain.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.widget.Toast

var isLocation: Boolean = false

fun getLatLngFromLocationName(context: Context, locationName: String): Pair<Double, Double>? {
    val geocoder = Geocoder(context)
    try {
        val addresses: List<Address> = geocoder.getFromLocationName(locationName, 1)
        if (addresses.isNotEmpty()) {
            val latitude = addresses[0].latitude
            val longitude = addresses[0].longitude
            isLocation = true
            return Pair(latitude, longitude)
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Invalid Location. Try again!", Toast.LENGTH_SHORT).show()
    }
    return null
}

fun getLocation(): Boolean {
    return isLocation
}