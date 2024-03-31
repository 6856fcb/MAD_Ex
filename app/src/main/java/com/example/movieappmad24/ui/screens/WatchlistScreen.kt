import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.ui.navigation.MovieBottomNavigationBar
import com.example.movieappmad24.ui.navigation.Screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavHostController
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.screens.MovieList

@Composable
fun WatchlistScreen(navController: NavHostController, movies: List<Movie>) {
    Scaffold(
        topBar = {
            SimpleTopMovieAppBar(
                title = "Watchlist",
                onBackPressed = { navController.navigateUp() },
            )
        },
        bottomBar = { MovieBottomNavigationBar(navController, Screen.Watchlist.route) }
    ) { innerPadding ->
        val watchlist = getMovies()
        MovieList(movies = watchlist, navController = navController, modifier = Modifier.padding(innerPadding))
    }
}
