package com.bunnarak.weatherapp.domain.datetime

import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// Define a function to get the current date and time as a formatted string
private fun getCurrentDateTimeString(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a"))
}

// Define a coroutine function to update the date and time asynchronously
@OptIn(DelicateCoroutinesApi::class)
@ExperimentalCoroutinesApi
fun updateDateTimeTextAsync(updateText: (String) -> Unit): Job {
    return GlobalScope.launch(Dispatchers.Main) {
        while (isActive) {
            // Get the current date and time as a formatted string
            val currentDateTimeString = getCurrentDateTimeString()

            // Update the text with the current date and time
            updateText(currentDateTimeString)

            // Delay for 30 seconds
            delay(30000)
        }
    }
}