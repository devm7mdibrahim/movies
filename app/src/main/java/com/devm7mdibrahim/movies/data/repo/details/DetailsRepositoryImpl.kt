package com.devm7mdibrahim.movies.data.repo.details

import com.devm7mdibrahim.movies.data.model.details.MovieDetailsResponse
import com.devm7mdibrahim.movies.data.remote.RemoteDataSource
import com.devm7mdibrahim.movies.utils.DataState
import com.devm7mdibrahim.movies.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkHelper: NetworkHelper,
) : DetailsRepository {

    private val _movies = MutableStateFlow<DataState<MovieDetailsResponse>>(DataState.Idle)

    override suspend fun getMovieDetails(movieId: Int): Flow<DataState<MovieDetailsResponse>> =
        flow {
            _movies.emit(DataState.Loading)

            if (networkHelper.isNetworkConnected()) {
                try {
                    val movieResponse = remoteDataSource.getMovieDetails(movieId)

                    if (movieResponse.isSuccessful) {
                        movieResponse.body()?.let {
                            _movies.emit(DataState.Error("movie not found!"))
                        }
                            ?: _movies.emit(DataState.Error("something went wrong, please check internet connection!"))
                    } else {
                        _movies.emit(DataState.Error("something went wrong, please check internet connection!"))
                    }
                } catch (e: Exception) {
                    _movies.emit(DataState.Error("something went wrong, please check internet connection!"))
                }
            } else {
                _movies.emit(DataState.Error("no internet connection!"))
            }
        }
}