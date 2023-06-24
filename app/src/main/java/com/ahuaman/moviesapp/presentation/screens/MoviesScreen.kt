package com.ahuaman.moviesapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.moviesapp.domain.MovieDomain
import com.ahuaman.moviesapp.presentation.composables.HorizontalMovieItem
import com.ahuaman.moviesapp.presentation.composables.VerticalMovieItem
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    moviesList: List<MovieDomain>,
    onClickNavigateToDetails: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        var searchQuery by rememberSaveable { mutableStateOf("") }

        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(40.dp)),
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            onSearch = { query ->
                // Handle search ImeAction.Search here
            },
            active = true,
            onActiveChange = { isActive ->
            },
            placeholder = { Text("Hinted search text") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            //trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) }
        ) {
            // Show suggestions here
            // for example a LazyColumn with suggestion items
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            content = {
            items(moviesList) {
                HorizontalMovieItem(
                    title = it.title,
                    description = it.overview,
                    imageUrl = it.poster_path,
                    rating = it.vote_average,
                    realeaseDate = it.release_date?: "",
                    onClick = { onClickNavigateToDetails(it.id) }
                )

                if(it == moviesList.last()) {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        })
    }

}


@Preview
@Composable
fun MoviesScreenPrev() {
    val moviesTests = listOf<MovieDomain>(
        MovieDomain(
            id = 1,
            title = "Ant-Man y la Avispa: Quantumanía",
            overview = "La pareja de superhéroes Scott Lang y Hope van Dyne regresa para continuar sus aventuras como Ant-Man y la Avispa. Los dos, junto a los padres de Hope, Hank Pym y Janet van Dyne y la hija de Scott, Cassie Lang, se dedican a explorar el Mundo Cuántico, interactuando con nuevas y extrañas criaturas y embarcándose en una aventura que les llevará más allá de los límites de lo que creían posible.",
            poster_path = "https://image.tmdb.org/t/p/original/lKHy0ntGPdQeFwvNq8gK1D0anEr.jpg",
            vote_average = 6.5f,
            release_date = "2022-02-17"
        ),
        MovieDomain(
            id = 2,
            title = "Sisu",
            overview = "En lo profundo de la naturaleza salvaje de Laponia, Aatami Korpi está buscando oro, pero después de tropezar con una patrulla nazi, comienza una persecución impresionante y hambrienta de oro a través de la naturaleza salvaje de Laponia destruida y minada.",
            poster_path = "https://image.tmdb.org/t/p/original/t9VXZkgcxpIwfPUKAWOOONs0vHv.jpg",
            vote_average = 7.4f,
            release_date = "2021-10-01"
        ),
    )

    MoviesScreen(
        moviesList = moviesTests,
        onClickNavigateToDetails = {
            Timber.d("onClickNavigateToDetails: $it")
        }
    )
}