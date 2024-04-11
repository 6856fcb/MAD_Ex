package com.example.movieappmad24.ui.view.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.Movie

@Composable
fun MovieCard(
    movie: Movie,
    expanded: Boolean,
    onExpandToggle: () -> Unit,
    onFavoriteClick: (String) -> Unit,
    isFavorite: Boolean,
    navController: NavHostController
) {
    Card(modifier = Modifier.padding(10.dp)) {
        Column {
            MovieHeader(
                movie = movie,
                expanded = expanded,
                onExpandToggle = onExpandToggle,
                onFavoriteClick = { onFavoriteClick(movie.id) },
                isFavorite = isFavorite,
                navController = navController
            )
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Text(text = "Director: ${movie.director}", modifier = Modifier.padding(bottom = 8.dp))
                    Text(text = "Year: ${movie.year}")
                }
            }
        }
    }
}


@Composable
fun MovieHeader(
    movie: Movie,
    expanded: Boolean,
    onExpandToggle: () -> Unit,
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean,
    navController: NavHostController
) {
    Box(modifier = Modifier.background(color = Color.White)) {
        MovieImage(imageUrl = movie.images.firstOrNull() ?: "", navController = navController, movieId = movie.id)
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Add to Watchlist",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(24.dp)
                .clickable(onClick = onFavoriteClick)
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onExpandToggle() }
            .padding(8.dp)
    ) {
        Text(
            text = movie.title,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = if (expanded) "Collapse" else "Expand"
        )
    }
}
@Composable
fun MovieImage(imageUrl: String, navController: NavHostController, movieId: String) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = { crossfade(true) }
        ),
        contentDescription = "Movie Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                navController.navigate("detailsScreen/$movieId") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        contentScale = ContentScale.Crop
    )
}

