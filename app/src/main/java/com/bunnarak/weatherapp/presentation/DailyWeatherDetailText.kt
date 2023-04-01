package com.bunnarak.weatherapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DailyWeatherDetailText (
    icon: ImageVector,
    iconTint: Color = Color.White,
    value: String,
    rotation: Float,
    animateBack: Float,
) {
    Row (
        modifier = Modifier.padding(start=5.dp)
    ) {
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.graphicsLayer {
                alpha = animateBack
                rotationY = rotation
            }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(25.dp)
                .graphicsLayer {
                    alpha = animateBack
                    rotationY = rotation
                }
        )
    }

}