package com.bunnarak.weatherapp.presentation

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bunnarak.weatherapp.R
import com.bunnarak.weatherapp.domain.location.*
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
            viewModel.loadDailyWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        setContent {
            val (locationName, setLocationName) = remember { mutableStateOf("") }
            viewModel.state.weatherInfo?.currentWeatherData?.weatherType?.let { data ->
                WeatherAppTheme {
                    Box(
                        modifier = data.modifier.fillMaxSize()
                    ) {
                        Column (
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .fillMaxSize()
                        ) {
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
                            WeatherForecast(state = viewModel.state)
                            DailyForecast(state = viewModel.state2)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(25.dp),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Button(
                                modifier = Modifier
                                    .size(60.dp),
                                shape = CircleShape,
                                onClick = {
                                    GlobalScope.launch(Dispatchers.IO) {
                                        viewModel.setCurrentLocation()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White
                                ),
                                content = {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_my_location_24),
                                        tint = Color.Red,
                                        contentDescription = "",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            )
                        }

//                        if (viewModel.state.isLoading) {
//                            CircularProgressIndicator(
//                                modifier = Modifier.align(Alignment.Center)
//                            )
//                        }

                        viewModel.state.error?.let {error ->
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}