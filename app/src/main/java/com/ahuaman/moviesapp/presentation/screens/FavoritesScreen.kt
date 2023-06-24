package com.ahuaman.moviesapp.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.moviesapp.domain.MovieDomain
import com.ahuaman.moviesapp.presentation.composables.VerticalMovieItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(
    onClickNavigateToDetails: () -> Unit,
    favoriteMovies: List<MovieDomain>,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 0.dp,
        horizontalArrangement = Arrangement.Center,
        content = {
            items(favoriteMovies){
                VerticalMovieItem(
                    title = it.title,
                    release = it.overview,
                    imageUrl = it.poster_path,
                    onClick = onClickNavigateToDetails
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewFavoritesScreen() {
    FavoritesScreen(
        onClickNavigateToDetails = {},
        favoriteMovies = listOf(
            MovieDomain(
                id = 1,
                title = "Title",
                overview = "Release",
                poster_path = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                vote_average = 7.5f
            ),
            MovieDomain(
                id = 2,
                title = "Title",
                overview = "Release",
                poster_path = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                vote_average = 7.5f
            ),
        )
    )
}