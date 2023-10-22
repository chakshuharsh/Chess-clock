package com.example.chessclock

import UI.HomeScreenApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.chessclock.ui.theme.ChessClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChessClockTheme {
                // A surface container using the 'background' color from the theme
              HomeScreenApp()
            }
        }
    }
}




