package com.bunnarak.weatherapp.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bunnarak.weatherapp.R

// Set of Material typography styles to start with
val JoseFinSans = FontFamily(
    Font(R.font.josefinsans_bold, FontWeight.Bold),
    Font(R.font.josefinsans_regular)
)

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = JoseFinSans,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),

    body1 = TextStyle(
        fontFamily = JoseFinSans,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)