package com.ahuaman.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.ahuaman.moviesapp.presentation.navigation.RootNavigationGraph
import com.ahuaman.moviesapp.presentation.screens.DashboardScreen
import com.ahuaman.moviesapp.ui.theme.MoviesappTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesappTheme {

                // A surface container using the 'background' color from the theme
                RootNavigationGraph()
            }
        }
    }
}
