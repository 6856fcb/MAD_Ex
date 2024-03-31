package com.example.movieappmad24.ui.screens

import SimpleTopMovieAppBar
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.ui.view.components.MovieCard

@Composable
fun DetailScreen(navController: NavHostController, movie: Movie) {
        Scaffold(
                topBar = {
                        SimpleTopMovieAppBar(
                                title = movie.title,
                                showBackButton = true,
                                onBackPressed = { navController.navigateUp()
                                }
                        )
                }
        ) { innerPadding ->
                Column(
                        modifier = Modifier
                                .padding(innerPadding)
                                .verticalScroll(rememberScrollState())
                ) {
                        MovieCard(movie = movie, expanded = false, onExpandToggle = {}, navController)
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyRow(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                                items(movie.images) { imageUrl ->
                                        Card(
                                                modifier = Modifier
                                                        .padding(end = 8.dp)
                                                        .size(120.dp)
                                        ) {
                                                Image(
                                                        painter = rememberImagePainter(
                                                                data = imageUrl,
                                                                builder = {
                                                                        crossfade(true)
                                                                }
                                                        ),
                                                        contentDescription = null,
                                                        modifier = Modifier
                                                                .fillMaxSize()
                                                                .padding(4.dp),
                                                        contentScale = ContentScale.Crop
                                                )
                                        }
                                }
                        }
                }
        }
}