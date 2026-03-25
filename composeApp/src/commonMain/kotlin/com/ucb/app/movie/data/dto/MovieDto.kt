package com.ucb.app.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    @SerialName("original_title")
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String
)
