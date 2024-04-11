package com.example.movieappmad24.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

class MoviesViewModel : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>(getMovies())
    val movieList: LiveData<List<Movie>> = _movieList

    private val _favoriteMoviesIds = MutableLiveData<Set<String>>(setOf())
    val favoriteMoviesIds: LiveData<Set<String>> = _favoriteMoviesIds

    fun toggleFavorite(movieId: String) {
        val currentFavorites = _favoriteMoviesIds.value ?: setOf()
        _favoriteMoviesIds.value = if (currentFavorites.contains(movieId)) {
            currentFavorites - movieId
        } else {
            currentFavorites + movieId
        }
    }
}

