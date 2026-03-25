package com.ucb.app.movie.presentation.state

sealed interface MovieEffect {
    data class ShowError(val message: String) : MovieEffect
}
