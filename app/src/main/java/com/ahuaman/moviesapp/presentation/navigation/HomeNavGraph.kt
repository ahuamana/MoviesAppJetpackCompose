package com.ahuaman.moviesapp.presentation.navigation

import androidx.compose.runtime.Composable
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
            MoviesScreen()
        }
        composable(HomeScreen.FavoritesHomeScreen.route) {
            FavoritesScreen(
                onClickNavigateToDetails = {
                    navController.navigate(route = Graph.DETAILS)
                }
            )
        }
        detailsNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ){
        composable(DetailsScreen.Information.route) {
            DetailsMovieScreen(
                navController = navController,
            )
        }
    }


}


sealed class DetailsScreen(val route:String){
    object Information:DetailsScreen("information_screen")
}

sealed class HomeScreen(val route:String, val int: Int, val title:String){
    object MoviesHomeScreen:HomeScreen("movies_screen", R.drawable.ic_movie, "Movies")
    object FavoritesHomeScreen:HomeScreen("favorites_screen", R.drawable.ic_favorite, "Favorites")
}


object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}