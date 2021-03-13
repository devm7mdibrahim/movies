package com.devm7mdibrahim.movies.data.remote

import com.devm7mdibrahim.movies.data.model.details.MovieDetailsResponse
import com.devm7mdibrahim.movies.data.model.movies.MoviesResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiService) :
    RemoteDataSource {
    override suspend fun getMovies(): Response<MoviesResponse> = apiService.getMovies()
    override suspend fun getMovieDetails(movieId: Int): Response<MovieDetailsResponse> =
        apiService.getMovieDetails(movieId)
}