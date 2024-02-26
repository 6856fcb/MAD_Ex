package com.example.movieappmad24

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.view.components.MovieBottomNavigationBar
import com.example.movieappmad24.ui.view.components.MovieList
import com.example.movieappmad24.ui.view.components.MovieTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                MainContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent() {
    val movies = remember { mutableStateOf(getMovies()) }

    Scaffold(
        topBar = { MovieTopAppBar() },
        bottomBar = { MovieBottomNavigationBar() }
    ) {
        MovieList(movies = movies.value)
    }
}
