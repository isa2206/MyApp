package com.ucb.app.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.movie.domain.model.MovieModel
import com.ucb.app.movie.domain.usecase.GetMoviesUseCase
import com.ucb.app.movie.presentation.state.MovieEffect
import com.ucb.app.movie.presentation.state.MovieEvent
import com.ucb.app.movie.presentation.state.MovieUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MovieUiState())
    val state: StateFlow<MovieUiState> = _state.asStateFlow()

    private val _effects = MutableSharedFlow<MovieEffect>()
    val effects: SharedFlow<MovieEffect> = _effects.asSharedFlow()

    init {
        onEvent(MovieEvent.GetMovies)
    }

    fun onEvent(event: MovieEvent) {
        when (event) {
            is MovieEvent.GetMovies -> fetchMovies()
            is MovieEvent.SetRating -> setRating(event.movieId, event.rating)
            is MovieEvent.SelectMovie -> selectMovie(event.movie)
        }
    }

    private fun fetchMovies() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val movies = getMoviesUseCase.invoke()
                _state.update { it.copy(isLoading = false, movies = movies) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
                _effects.emit(MovieEffect.ShowError(e.message ?: "Unknown error"))
            }
        }
    }

    private fun setRating(movieId: Int, rating: Int) {
        _state.update { currentState ->
            val updatedMovies = currentState.movies.map { movie ->
                if (movie.id == movieId) {
                    val newRating = if (movie.rating == rating) 0 else rating
                    movie.copy(rating = newRating)
                } else {
                    movie
                }
            }
            // Update selectedMovie if it's the one being rated
            val updatedSelectedMovie = if (currentState.selectedMovie?.id == movieId) {
                val newRating = if (currentState.selectedMovie.rating == rating) 0 else rating
                currentState.selectedMovie.copy(rating = newRating)
            } else {
                currentState.selectedMovie
            }
            currentState.copy(movies = updatedMovies, selectedMovie = updatedSelectedMovie)
        }
    }

    private fun selectMovie(movie: MovieModel?) {
        _state.update { it.copy(selectedMovie = movie) }
    }
}
