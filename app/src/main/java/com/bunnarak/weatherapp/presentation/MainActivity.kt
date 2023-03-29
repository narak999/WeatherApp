package com.bunnarak.weatherapp.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bunnarak.weatherapp.domain.location.setLat
import com.bunnarak.weatherapp.domain.location.setLong
import com.bunnarak.weatherapp.domain.location.getLatLngFromLocationName
import com.bunnarak.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = this

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        setContent {
            val (locationName, setLocationName) = remember { mutableStateOf("") }
            WeatherAppTheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(
                            id = viewModel.state.weatherInfo?.currentWeatherData?.weatherType?.backgroundImage
                                ?: com.bunnarak.weatherapp.R.drawable.default_image
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column (modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(23.dp))
                        SearchBar(
                            hint = "Enter location name",
                            onSearch = {query ->
                                setLocationName(query)
                                GlobalScope.launch(Dispatchers.IO) {
                                    Log.d(TAG, "Search Bar: ${Thread.currentThread().name}")
                                    val coordinate: Pair<Double, Double>? = getLatLngFromLocationName(context, query)
                                    if (coordinate != null) {
                                        setLat(coordinate.first)
                                        setLong(coordinate.second)
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        WeatherCard(
                            viewModel = viewModel,
                            backgroundColor = Color(0xFF009DFF),
                            context = context
                        )
                    }
                }
            }
        }
    }
}