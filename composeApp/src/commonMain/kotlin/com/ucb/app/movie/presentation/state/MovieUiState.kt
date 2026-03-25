package com.ucb.app.movie.presentation.state

import com.ucb.app.movie.domain.model.MovieModel

data class MovieUiState(
    val isLoading: Boolean = false,
    val movies: List<MovieModel> = emptyList(),
    val selectedMovie: MovieModel? = null,
    val error: String? = null
)
