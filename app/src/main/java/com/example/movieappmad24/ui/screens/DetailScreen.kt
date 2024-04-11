import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.view.components.MovieCard
import com.example.movieappmad24.ui.viewmodel.MoviesViewModel

@Composable
fun DetailScreen(viewModel: MoviesViewModel, navController: NavHostController, movieId: String) {
        val expanded = remember { mutableStateOf(false) }
        val context = LocalContext.current
        val movie = getMovies().find { it.id == movieId } ?: throw IllegalStateException("Movie with ID $movieId not found.")
        val favoriteMovieIds by viewModel.favoriteMoviesIds.observeAsState(initial = setOf())
        val player = remember { ExoPlayer.Builder(context).build() }
        val isFavorite = movie.id in favoriteMovieIds
        DisposableEffect(key1 = context) {
                onDispose {
                        player.release()
                }
        }

        LaunchedEffect(key1 = movie.trailer) {
                val resourceName = movie.trailer
                val resourceId = context.resources.getIdentifier(resourceName, "raw", context.packageName)
                val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/$resourceId")
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
        }

        Scaffold(
                topBar = {
                        SimpleTopMovieAppBar(
                                title = movie.title,
                                showBackButton = true,
                                onBackPressed = { navController.navigateUp() }
                        )
                }
        ) { innerPadding ->
                Column(
                        modifier = Modifier
                                .padding(innerPadding)
                                .verticalScroll(rememberScrollState())
                ) {
                        MovieCard(
                                movie = movie,
                                expanded = expanded.value,
                                onExpandToggle = { expanded.value = !expanded.value },
                                onFavoriteClick = { movieId -> viewModel.toggleFavorite(movieId) },
                                isFavorite = isFavorite,
                                navController = navController
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        AndroidView(
                                factory = { context ->
                                        PlayerView(context).apply {
                                                this.player = player
                                        }
                                },
                                modifier = Modifier.fillMaxWidth()
                                                        .height(214.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        LazyRow(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                                items(movie.images) { imageUrl ->
                                        Card(
                                                modifier = Modifier
                                                        .padding(end = 8.dp)
                                                        .size(220.dp)
                                        ) {
                                                Image(
                                                        painter = rememberImagePainter(data = imageUrl, builder = { crossfade(true) }),
                                                        contentDescription = null,
                                                        modifier = Modifier.fillMaxSize().padding(4.dp),
                                                        contentScale = ContentScale.Crop
                                                )
                                        }
                                }
                        }
                }
        }

}
