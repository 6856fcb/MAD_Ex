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
import androidx.navigation.NavHostController
import androidx.compose.runtime.livedata.observeAsState
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.ui.view.components.MovieCard
import com.example.movieappmad24.ui.viewmodel.MoviesViewModel

@Composable
fun HomeScreen(viewModel: MoviesViewModel, navController: NavHostController) {

        val movies by viewModel.movieList.observeAsState(initial = listOf())
        val favoriteMovieIds by viewModel.favoriteMoviesIds.observeAsState(initial = setOf())
        val expandedMovieId = remember { mutableStateOf<String?>(null) }
        Scaffold(
                topBar = {
                        SimpleTopMovieAppBar(title = "Home", onBackPressed = {})
                },
        ) { innerPadding ->
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(movies) { movie ->

                                val isFavorite = movie.id in favoriteMovieIds

                                MovieCard(
                                        movie = movie,
                                        expanded = expandedMovieId.value == movie.id,
                                        onExpandToggle = {
                                                expandedMovieId.value = if (expandedMovieId.value == movie.id) null else movie.id
                                                         },
                                        onFavoriteClick = { movieId ->
                                                viewModel.toggleFavorite(movieId)
                                        },
                                        isFavorite = isFavorite,
                                        navController = navController
                                )
                                Spacer(Modifier.height(8.dp))
                        }
                }
        }
}


@Composable
fun MovieList(
        movies: List<Movie>,
        viewModel: MoviesViewModel,
        navController: NavHostController,
        modifier: Modifier = Modifier
) {
        val expandedMovieId = remember { mutableStateOf<String?>(null) }
        val favoriteMovieIds by viewModel.favoriteMoviesIds.observeAsState(initial = setOf())

        LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
                items(movies) { movie ->
                        val isFavorite = movie.id in favoriteMovieIds

                        MovieCard(
                                movie = movie,
                                expanded = expandedMovieId.value == movie.id,
                                onExpandToggle = {
                                        expandedMovieId.value = if (expandedMovieId.value == movie.id) null else movie.id
                                },
                                onFavoriteClick = { movieId -> viewModel.toggleFavorite(movieId) },
                                isFavorite = isFavorite,
                                navController = navController
                        )
                        Spacer(Modifier.height(8.dp))
                }
        }
}





