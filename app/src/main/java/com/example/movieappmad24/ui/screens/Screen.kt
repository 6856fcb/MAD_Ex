package com.example.movieappmad24.navigation

sealed class Screen(val route: String) {
    object Home : Screen("homeScreen")
    object Detail : Screen("detailsScreen/{movieId}") {
        fun createRoute(movieId: String) = "detailsScreen/$movieId"
    }
    object Watchlist : Screen("watchlist")
}
