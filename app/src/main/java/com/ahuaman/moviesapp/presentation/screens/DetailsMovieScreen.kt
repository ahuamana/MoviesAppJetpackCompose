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
import com.ahuaman.moviesapp.domain.MovieDetailDomain
import com.ahuaman.moviesapp.presentation.composables.ErrorScreen
import com.ahuaman.moviesapp.presentation.composables.LoadingScreen
import com.ahuaman.moviesapp.usecases.GetDetailsMovieResult

@Composable
fun DetailsMovieScreen(
    navController: NavController,
    stateMovieDetail: GetDetailsMovieResult,
    onClickFavorite: (MovieDetailDomain) -> Unit,
    isFavoriteMovie:Boolean,
) {
    var isLoading by remember { mutableStateOf(false)}
    var isError by remember { mutableStateOf(false)}
    var item by remember { mutableStateOf(MovieDetailDomain())}


    LaunchedEffect(key1 = stateMovieDetail){
        when(stateMovieDetail) {
            is GetDetailsMovieResult.Success -> {
                isLoading = false
                isError = false
                item = stateMovieDetail.data
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
        when{
            isLoading -> {
                LoadingScreen()
            }
            isError -> {
                ErrorScreen()
            }
            else -> {
                DetailsMovieContent(
                    onClickBack = {
                        navController.popBackStack()
                    },
                    onClickFavorite = { onClickFavorite(item) },
                    title = item.title?: "",
                    description = item.overview?: "",
                    imageBackdrop = item.backdrop_path?: "",
                    imagePoster = item.poster_path?: "",
                    genres = item.genres?: listOf(),
                    releaseDate = item.release_date?: "",
                    voteAverage = item.vote_average?.toString()?: "",
                    runtime = item.runtimeWithMinutes?: "",
                    isFavoriteMovie = isFavoriteMovie
                )
            }
        }
    }
}

