package com.ahuaman.moviesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ahuaman.moviesapp.R
import com.ahuaman.moviesapp.presentation.screens.DetailsMovieScreen
import com.ahuaman.moviesapp.presentation.screens.FavoritesScreen
import com.ahuaman.moviesapp.presentation.screens.MoviesScreen
import com.ahuaman.moviesapp.presentation.screens.DashboardScreen
import com.ahuaman.moviesapp.presentation.viewmodels.DetailsMovieViewModel
import com.ahuaman.moviesapp.presentation.viewmodels.MoviesViewModel
import timber.log.Timber


@Composable
fun RootNavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME
    ) {
        composable(route = Graph.HOME) {
            DashboardScreen()
        }

        composable(route = Graph.DETAILS){
            //DetailsMovieScreen()
        }

    }
}


@Composable
fun homeNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeScreen.MoviesHomeScreen.route,
    ) {

        composable(HomeScreen.MoviesHomeScreen.route) {
            val moviesViewModel = hiltViewModel<MoviesViewModel>()

            val movies by moviesViewModel.movies.collectAsStateWithLifecycle()
            MoviesScreen(
                moviesList = movies,
                onClickNavigateToDetails = { movieID ->
                    Timber.d("movieId saved: $movieID")
                    navController.navigate(route = Graph.DETAILS + "/$movieID")
                }
            )
        }
        composable(HomeScreen.FavoritesHomeScreen.route) {
            FavoritesScreen(
                onClickNavigateToDetails = {
                    navController.navigate(route = Graph.DETAILS)
                },
                favoriteMovies = emptyList()
            )
        }
        detailsNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS + "/{movieId}",
        startDestination = DetailsScreen.Information.route
    ){

        composable(DetailsScreen.Information.route) {
            val movieId = it.arguments?.getString("movieId")?: ""
            val detailsMovieViewModel = hiltViewModel<DetailsMovieViewModel>()
            val stateMovieDetail by detailsMovieViewModel.detailsMovie.collectAsStateWithLifecycle()
            val isFavoriteMovie by detailsMovieViewModel.isFavoriteMovie.collectAsStateWithLifecycle()
            Timber.d("movieId retrieved: ${movieId}")

            DetailsMovieScreen(
                navController = navController,
                stateMovieDetail = stateMovieDetail,
                onClickFavorite = { movieDetail ->
                    detailsMovieViewModel.saveOrRemoveFavoriteMovie(movieDetail)
                },
                isFavoriteMovie = isFavoriteMovie,
            )
        }
    }


}


sealed class DetailsScreen(val route:String){
    object Information:DetailsScreen("information_screen")
}

sealed class HomeScreen(val route:String, val icon: Int, val title:String){
    object MoviesHomeScreen:HomeScreen("movies_screen", R.drawable.ic_movie, "Movies")
    object FavoritesHomeScreen:HomeScreen("favorites_screen", R.drawable.ic_love, "Favorites")
}


object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}