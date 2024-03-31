package com.example.movieappmad24.ui.screens

import SimpleTopMovieAppBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.navigation.MovieBottomNavigationBar
import com.example.movieappmad24.ui.view.components.MovieCard
import com.example.movieappmad24.ui.view.components.MovieHeader
import com.example.movieappmad24.ui.view.components.movieTile.MovieDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen(navController: NavHostController) {
        Scaffold(
                topBar = {
                        SimpleTopMovieAppBar(
                                title = "home", onBackPressed = {})
                },
        ) { innerPadding ->
                MovieList(
                        movies = getMovies(),
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                )
        }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieList(movies: List<Movie>, navController: NavHostController, modifier: Modifier = Modifier) {
        // mutableState object?? -> see docs for ref
        val expandedMovieId = remember { mutableStateOf<String?>(null) }

        LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
                items(movies) { movie ->
                        MovieCard(
                                movie = movie,
                                expanded = expandedMovieId.value == movie.id,
                                onExpandToggle = {
                                        expandedMovieId.value = if (expandedMovieId.value == movie.id) null else movie.id
                                },
                                navController = navController
                        )
                        Spacer(Modifier.height(8.dp))
                }
        }
}




