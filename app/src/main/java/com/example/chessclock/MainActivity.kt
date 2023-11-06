package com.example.chessclock

import UI.ChessTimeViewModel
import UI.HomeScreenApp
import UI.Screen
import UI.TimerControls
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
            val viewModel= remember {  //ViewModel that manages UI state
                ChessTimeViewModel()
            }
            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.name,
                enterTransition = { // Slide-in transition
                    slideInHorizontally(
                        initialOffsetX = { -1000 }, // Initial X offset (offscreen to the left)
                        animationSpec = tween(300)
                    )
                },
                exitTransition = { ExitTransition.None }
            ) {
                composable(Screen.HomeScreen.name)

                     {
                    HomeScreenApp(navController,viewModel)

                }

                composable(Screen.TimerControl.name,
                    enterTransition = { // Slide-in transition
                        slideInHorizontally(
                            initialOffsetX = { 1000 }, // Initial X offset (offscreen to the left)
                            animationSpec = tween(300)
                        )
                                      },
                    exitTransition = { ExitTransition.None }
                ) {
                    TimerControls(navController,viewModel)

                }
            }
        }
    }
}




