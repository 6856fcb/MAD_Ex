package com.example.movieappmad24.ui.view.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.ui.view.components.movieTile.MovieCard

@Composable
fun MovieList(movies: List<Movie>) {
    LazyColumn {
        items(movies) { movie ->
            MovieCard(movie = movie)
        }
    }
}
