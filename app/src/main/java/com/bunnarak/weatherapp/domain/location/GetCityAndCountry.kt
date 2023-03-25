package com.bunnarak.weatherapp.domain.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.util.Locale

suspend fun getCityAndCountry (
    context: Context,
    lat: Double,
    lng: Double
): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(lat, lng, 1)
        if (addresses.isEmpty()) {
            return null
        }
        val city = addresses[0].locality ?: ""
        val country = addresses[0].countryCode ?: ""
        return "$city, $country"
}