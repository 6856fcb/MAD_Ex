import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.movieappmad24.ui.screens.MovieList
import com.example.movieappmad24.ui.viewmodel.MoviesViewModel

@Composable
fun WatchlistScreen(viewModel: MoviesViewModel, navController: NavHostController) {
    val favoriteMovieIds by viewModel.favoriteMoviesIds.observeAsState(initial = setOf())
    val favoriteMovies = viewModel.movieList.value?.filter { it.id in favoriteMovieIds } ?: listOf()

    Scaffold(
        topBar = {
            SimpleTopMovieAppBar(
                title = "Watchlist",
                onBackPressed = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        MovieList(movies = favoriteMovies, viewModel = viewModel, navController = navController, modifier = Modifier.padding(innerPadding))
    }
}


