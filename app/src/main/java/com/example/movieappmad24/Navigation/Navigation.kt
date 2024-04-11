package com.example.movieappmad24.ui.navigation

import DetailScreen
import WatchlistScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieappmad24.navigation.Screen
import com.example.movieappmad24.ui.screens.HomeScreen
import com.example.movieappmad24.ui.viewmodel.MoviesViewModel


@Composable
fun Navigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val viewModel: MoviesViewModel = viewModel()

    Scaffold(
        bottomBar = { MovieBottomNavigationBar(navController, currentRoute) },
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(paddingValues)) {
            composable(Screen.Home.route) {
                HomeScreen(viewModel, navController)
            }
            composable(Screen.Watchlist.route) {
                WatchlistScreen(viewModel, navController)
            }
            composable(Screen.Detail.route) { backStackEntry ->
                backStackEntry.arguments?.getString("movieId")?.let { movieId ->
                    val lifecycleOwner = LocalLifecycleOwner.current
                    DetailScreen(viewModel, navController, movieId)
                }
            }

        }
    }
}



@Composable
fun MovieBottomNavigationBar(navController: NavController, currentRoute: String?) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text(text = "Home") },
            selected = currentRoute == Screen.Home.route,
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Watchlist") },
            label = { Text("Watchlist") },
            selected = currentRoute == Screen.Watchlist.route,
            onClick = {
                navController.navigate(Screen.Watchlist.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                }
            }
        )
    }
}
