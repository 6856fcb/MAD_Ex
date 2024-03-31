import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopMovieAppBar(
    title: String,
    showBackButton: Boolean = false,
    onBackPressed: () -> Unit,
    actionIcon: ImageVector? = null,
    onActionClicked: () -> Unit = {}
) {
    if (showBackButton) {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                actionIcon?.let {
                    IconButton(onClick = onActionClicked) {
                        Icon(imageVector = it, contentDescription = "Action")
                    }
                }
            }
        )
    } else {
        TopAppBar(
            title = { Text(title) },
            actions = {
                actionIcon?.let {
                    IconButton(onClick = onActionClicked) {
                        Icon(imageVector = it, contentDescription = "Action")
                    }
                }
            }
        )
    }
}
