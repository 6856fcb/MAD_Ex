package com.example.movieappmad24.ui.view.components.movieTile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieappmad24.models.Movie

@Composable
fun MovieCard(movie: Movie) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.padding(10.dp)) {
        Column {
            MovieHeader(movie, expanded) { expanded = !expanded }
            MovieDetails(movie, expanded)
        }
    }
}
