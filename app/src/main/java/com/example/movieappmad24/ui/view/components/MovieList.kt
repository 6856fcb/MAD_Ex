package com.example.movieappmad24.ui.view.components

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.ui.view.components.movieTile.MovieCard


@Composable
fun MovieList(movies: List<Movie>, padding: PaddingValues) {
    LazyColumn (modifier = Modifier.padding(padding)) {
        items(movies) { movie ->
            MovieCard(movie = movie)
        }
    }
}
