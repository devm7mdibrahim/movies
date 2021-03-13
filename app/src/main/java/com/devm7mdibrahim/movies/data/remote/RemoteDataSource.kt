package com.devm7mdibrahim.movies.data.remote

import com.devm7mdibrahim.movies.data.model.details.MovieDetailsResponse
import com.devm7mdibrahim.movies.data.model.movies.MoviesResponse
import retrofit2.Response
import retrofit2.http.Path

interface RemoteDataSource {
    suspend fun getMovies(): Response<MoviesResponse>
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): Response<MovieDetailsResponse>
}