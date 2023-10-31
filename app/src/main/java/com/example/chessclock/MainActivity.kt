package com.example.chessclock

import UI.HomeScreenApp
import UI.Screen
import UI.TimerControls
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
                // A surface container using the 'background' color from the theme

            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.name
            ) {
                composable(Screen.HomeScreen.name) {
                    HomeScreenApp(navController)
                }

                composable(Screen.TimerControl.name) {
                    TimerControls(navController)
                }
            }
        }
    }
}




