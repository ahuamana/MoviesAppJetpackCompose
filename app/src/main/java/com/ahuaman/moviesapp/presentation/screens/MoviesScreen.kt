package com.ahuaman.moviesapp.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahuaman.moviesapp.R
import com.ahuaman.moviesapp.domain.MovieDomain
import com.ahuaman.moviesapp.presentation.composables.CustomEmptySearchScreen
import com.ahuaman.moviesapp.presentation.composables.CustomErrorScreenSomethingHappens
import com.ahuaman.moviesapp.presentation.composables.CustomNoInternetConnectionScreen
import com.ahuaman.moviesapp.presentation.composables.HorizontalMovieItem
import com.ahuaman.moviesapp.presentation.composables.LoadingScreen
import com.ahuaman.moviesapp.usecases.PopularMoviesResult
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    moviesList: PopularMoviesResult,
    onClickNavigateToDetails: (Int) -> Unit,
    onQueryChange: (String) -> Unit
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
            onQueryChange = {  queryChanged ->
                searchQuery = queryChanged // update the query state
                onQueryChange(queryChanged) // call the callback
                            },
            onSearch = { query ->
                // Handle search ImeAction.Search here
            },
            active = true,
            onActiveChange = { isActive ->
            },
            placeholder = { Text("Busca una película") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            //trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) }
        ) {
            // Show suggestions here
            // for example a LazyColumn with suggestion items
        }

        Spacer(modifier = Modifier.height(16.dp))

        HeaderMoviesScreen(
            searchQuery = searchQuery,
            onClickNavigateToDetails = onClickNavigateToDetails,
            popularMoviesState = moviesList
        )


    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeaderMoviesScreen(
    searchQuery: String,
    onClickNavigateToDetails: (Int) -> Unit,
    popularMoviesState:  PopularMoviesResult
) {
    var isErrorGeneral by rememberSaveable { mutableStateOf(false) }
    var isSuccess by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var isEmpty by rememberSaveable { mutableStateOf(false) }
    var isInternetError by rememberSaveable { mutableStateOf(false) }

    var popularMoviesList by rememberSaveable { mutableStateOf(listOf<MovieDomain>()) }

    LaunchedEffect(key1 = popularMoviesState){
        when(popularMoviesState){
            PopularMoviesResult.Empty ->{
                isLoading = false
                isErrorGeneral = false
                isInternetError = false
                isEmpty = true
            }
            is PopularMoviesResult.ErrorGeneral -> {
                isLoading = false
                isErrorGeneral = true
            }
            PopularMoviesResult.InternetError -> {
                isErrorGeneral = false
            }
            is PopularMoviesResult.Loading -> {
                isLoading = popularMoviesState.isLoading
                isErrorGeneral = false
            }
            is PopularMoviesResult.Success -> {
                isLoading = false
                isErrorGeneral = false
                isEmpty = false
                isInternetError = false
                isSuccess = true
                popularMoviesList = popularMoviesState.list
            }
        }
    }

    when {
        isLoading -> {
            LoadingScreen()
        }
        isErrorGeneral -> {
            CustomErrorScreenSomethingHappens(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }
        isInternetError -> {
            CustomNoInternetConnectionScreen(
                modifier = Modifier.padding(bottom = 180.dp),
            )
        }
        isEmpty -> {
            CustomEmptySearchScreen(
                Modifier.padding(bottom = 180.dp),
                description = stringResource(id = R.string.empty_screen_description_no_results, searchQuery)
            )
        }
        isSuccess -> {

            val visibleItems by rememberSaveable { mutableStateOf(popularMoviesList) }

            LazyColumn {
                items(popularMoviesList, key = { it.id }) {item ->

                    AnimatedVisibility(
                        visible = true,
                        //scaleOut
                        enter = scaleIn(
                            initialScale = 0.9f,
                            animationSpec = tween(700, easing = FastOutSlowInEasing)
                        ) + expandVertically(),
                        exit = scaleOut(
                            targetScale = 0.9f,
                            animationSpec = tween(700, easing = FastOutSlowInEasing)
                        ) + shrinkVertically()
                    ) {
                        HorizontalMovieItem(
                            title = item.title,
                            description = item.overview,
                            imageUrl = item.poster_path,
                            rating = item.vote_average,
                            realeaseDate = item.release_date ?: "",
                            onClick = { onClickNavigateToDetails(item.id) })

                        if (item == popularMoviesList.last()) {
                            Spacer(modifier = Modifier.height(80.dp))
                        }
                    }
                }
            }
        }
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
        moviesList = PopularMoviesResult.Success(moviesTests),
        onClickNavigateToDetails = {
            Timber.d("onClickNavigateToDetails: $it")
        },
        onQueryChange = {
            Timber.d("onQueryChange: $it")
        }
    )
}