package com.devm7mdibrahim.movies.data.repo.main

import com.devm7mdibrahim.movies.data.model.movies.Movie
import com.devm7mdibrahim.movies.data.remote.RemoteDataSource
import com.devm7mdibrahim.movies.utils.DataState
import com.devm7mdibrahim.movies.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkHelper: NetworkHelper
) : MainRepository {

    private val _movies = MutableStateFlow<DataState<List<Movie>>>(DataState.Idle)

    override suspend fun getMovies(): Flow<DataState<List<Movie>>> {
        return flow {
            _movies.emit(DataState.Loading)

            if (networkHelper.isNetworkConnected()) {
                try {
                    val moviesResponse = remoteDataSource.getMovies()

                    if (moviesResponse.isSuccessful) {
                        moviesResponse.body()?.let {
                            if (it.movies.isNullOrEmpty()) {
                                _movies.emit(DataState.Error("no movies found!"))
                            } else {
                                _movies.emit(DataState.Success(it.movies))
                            }
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
}