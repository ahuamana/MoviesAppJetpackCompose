package com.ahuaman.moviesapp.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ahuaman.moviesapp.presentation.composables.ErrorScreen
import com.ahuaman.moviesapp.presentation.composables.LoadingScreen
import com.ahuaman.moviesapp.usecases.GetDetailsMovieResult

@Composable
fun DetailsMovieScreen(
    navController: NavController,
    stateMovieDetail: GetDetailsMovieResult
) {
    var isLoading by remember { mutableStateOf(false)}
    var isError by remember { mutableStateOf(false)}
    var items by remember { mutableStateOf(listOf<String>())}

    LaunchedEffect(key1 = stateMovieDetail){
        when(stateMovieDetail) {
            is GetDetailsMovieResult.Success -> {
                isLoading = false
                isError = false

            }
            is GetDetailsMovieResult.Error -> {
                isLoading = false
                isError = true
            }
            is GetDetailsMovieResult.Loading -> {
                isLoading = stateMovieDetail.isLoading
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = isLoading) {
            LoadingScreen()
        }
        AnimatedVisibility(visible = !isLoading && !isError) {
            DetailsMovieContent()
        }

        AnimatedVisibility(visible = isError) {
            ErrorScreen()
        }

    }
}

