package com.ucb.app.movie.presentation.state

import com.ucb.app.movie.domain.model.MovieModel

sealed interface MovieEvent {
    data object GetMovies : MovieEvent
    data class SetRating(val movieId: Int, val rating: Int) : MovieEvent
    data class SelectMovie(val movie: MovieModel?) : MovieEvent
}
