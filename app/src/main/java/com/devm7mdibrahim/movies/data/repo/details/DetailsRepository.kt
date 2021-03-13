package com.devm7mdibrahim.movies.data.repo.details

import com.devm7mdibrahim.movies.data.model.details.MovieDetailsResponse
import com.devm7mdibrahim.movies.utils.DataState
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {
    suspend fun getMovieDetails(movieId: Int): Flow<DataState<MovieDetailsResponse>>
}