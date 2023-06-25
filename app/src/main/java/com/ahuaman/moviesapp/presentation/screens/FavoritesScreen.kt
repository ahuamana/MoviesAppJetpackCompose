package com.ahuaman.moviesapp.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.moviesapp.R
import com.ahuaman.moviesapp.domain.MovieDomain
import com.ahuaman.moviesapp.domain.local.FavoriteMoviesEntity
import com.ahuaman.moviesapp.presentation.composables.CustomEmptyStateScreen
import com.ahuaman.moviesapp.presentation.composables.VerticalMovieItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(
    onClickNavigateToDetails: (Int) -> Unit,
    favoriteMovies: List<FavoriteMoviesEntity>,
) {
    when{
        favoriteMovies.isEmpty() -> {
            CustomEmptyStateScreen(
                modifier = Modifier.padding(bottom = 180.dp),
                image = R.drawable.background_box_empty_state,
                title = stringResource(R.string.screen_empty_title_favorites),
                description = stringResource(R.string.screen_empty_description_favorites)
            )
        }
        else -> {
            LazyVerticalStaggeredGrid(
                modifier = Modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 0.dp,
                horizontalArrangement = Arrangement.Center,
                content = {
                    items(favoriteMovies){
                        VerticalMovieItem(
                            title = it.title,
                            release = it.overview,
                            imageUrl = it.poster_path,
                            onClick = { onClickNavigateToDetails(it.id) }
                        )

                        if(it == favoriteMovies.last()) {
                            Spacer(modifier = Modifier.height(80.dp))
                        }

                    }
                }
            )
        }
    }

}

@Preview
@Composable
fun PreviewFavoritesScreen() {
    FavoritesScreen(
        onClickNavigateToDetails = {},
        favoriteMovies = listOf(
            FavoriteMoviesEntity(
                id = 1,
                title = "Title",
                overview = "Release",
                poster_path = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                vote_average = 7.5f,
                release_date = "2021-08-11",
            ),
            FavoriteMoviesEntity(
                id = 2,
                title = "Title",
                overview = "Release",
                poster_path = "https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                vote_average = 7.5f,
                release_date = "2021-08-11",
            ),
        )
    )
}