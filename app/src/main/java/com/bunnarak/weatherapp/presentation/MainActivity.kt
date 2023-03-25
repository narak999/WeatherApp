package com.bunnarak.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bunnarak.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo(this)
        }
        permissionLauncher.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        setContent {
            WeatherAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
//                        .background(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(
//                                    Color(0xFF82DFFF),
//                                    Color(0xFFB8EDFF)
//                                )
//                            )
//                        )
                ) {
                    Image(
                        painter = painterResource(
                            id = viewModel.state.weatherInfo?.currentWeatherData?.weatherType?.backgroundImage
                                ?: com.bunnarak.weatherapp.R.drawable.default_image
                        ),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column (modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(30.dp))
//                        Row(
//                            modifier = Modifier
//                                .height(40.dp)
//                                .fillMaxWidth()
//                                .background(Color.Red)
//                        ) {
//                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        WeatherCard(
                            state = viewModel.state,
                            backgroundColor = Color(0xFF3278bf)
                        )
                    }
                }
            }
        }
    }
}