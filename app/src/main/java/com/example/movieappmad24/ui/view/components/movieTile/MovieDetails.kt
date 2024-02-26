package com.example.movieappmad24.ui.view.components.movieTile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieappmad24.models.Movie

@Composable
fun MovieDetails(movie: Movie, expanded: Boolean) {
    AnimatedVisibility(visible = expanded) {
        Card(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp).fillMaxWidth()) {
            Text(text = "Director: ${movie.director}", modifier = Modifier.padding(8.dp))
            Text(text = "Year: ${movie.year}", modifier = Modifier.padding(8.dp))
        }
    }
}
