package com.example.movieappmad24.ui.view.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopAppBar() {
    TopAppBar(
        title = { Text(text = "Movie App") }
    )
}
