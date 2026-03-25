package com.ucb.app.movie.data.repository

import com.ucb.app.movie.data.datasource.MovieRemoteDataSource
import com.ucb.app.movie.data.mapper.toModel
import com.ucb.app.movie.domain.model.MovieModel
import com.ucb.app.movie.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<MovieModel> {
        return remote.getMovies().map { it.toModel() }
    }
}
