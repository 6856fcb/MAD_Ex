package com.example.movieappmad24

import WatchlistScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.screens.HomeScreen
import com.example.movieappmad24.ui.screens.DetailScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    Scaffold(
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "homeScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("homeScreen") {
                HomeScreen(navController = navController)
            }
            composable("detailsScreen/{movieId}") { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")
                val movie = getMovies().find { it.id == movieId }
                if (movie != null) {
                    DetailScreen(navController = navController, movie = movie)
                }
            }
            composable("watchlist") {
                WatchlistScreen(navController = navController, movies = getMovies())
            }
        }
    }
}
