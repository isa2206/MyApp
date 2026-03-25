package com.ucb.app.movie.domain.model

data class MovieModel(
    val id: Int,
    val description: String,
    val title: String,
    val pathUrl: String,
    val rating: Int = 0
)