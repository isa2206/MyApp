package com.ucb.app.movie.data.datasource

import com.ucb.app.movie.data.dto.MovieDto

interface MovieRemoteDataSource {
    suspend fun getMovies(): List<MovieDto>
}
