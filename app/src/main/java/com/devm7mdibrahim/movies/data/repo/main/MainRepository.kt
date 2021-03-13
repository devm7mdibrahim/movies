package com.devm7mdibrahim.movies.data.repo.main

import com.devm7mdibrahim.movies.data.model.movies.Movie
import com.devm7mdibrahim.movies.utils.DataState
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMovies(): Flow<DataState<List<Movie>>>
}