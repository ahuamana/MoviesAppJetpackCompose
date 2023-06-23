package com.ahuaman.moviesapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahuaman.moviesapp.presentation.navigation.HomeScreen
import com.ahuaman.moviesapp.presentation.navigation.homeNavGraph
import timber.log.Timber


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DashboardScreen() {
    val navController:NavHostController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomBarCustom(navController = navController)
        }
    ) {
        //content
        homeNavGraph(navController = navController)
    }
}


@Composable
fun BottomBarCustom(navController: NavHostController){
    val menuItems = listOf(
        HomeScreen.MoviesHomeScreen,
        HomeScreen.FavoritesHomeScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = menuItems.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        BottomNavigation() {
            menuItems.forEach { screen ->
                //setup the alpha for the selected item
                val isSelectedMenu = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                val backgroundAlpha = if (isSelectedMenu) 1f else 0.6f

                BottomNavigationItem(
                    label = {
                        Text(text = screen.title, color = Color.White.copy(alpha = backgroundAlpha)) },
                    icon = { Icon(painterResource(id = screen.icon), contentDescription = screen.title, modifier = Modifier.graphicsLayer(alpha = backgroundAlpha)) },
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    selectedContentColor = Color.White,
                    unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        }
    }
}


