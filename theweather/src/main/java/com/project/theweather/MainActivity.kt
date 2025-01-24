package com.project.theweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.authentication.ui.SignInScreen
import com.project.uikit.TheWeatherTheme
import com.project.features.weather_main.WeatherMainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheWeatherTheme {
                //WeatherMainScreen()
                SignInScreen()
            }
        }
    }
}