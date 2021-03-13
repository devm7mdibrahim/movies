package com.devm7mdibrahim.movies.data.repo.details

import com.devm7mdibrahim.movies.data.model.details.MovieDetailsResponse
import com.devm7mdibrahim.movies.data.remote.RemoteDataSource
import com.devm7mdibrahim.movies.utils.DataState
import com.devm7mdibrahim.movies.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val networkHelper: NetworkHelper,
    private val IOContext: CoroutineContext
) : DetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): Flow<DataState<MovieDetailsResponse>> =
        flow {
            emit(DataState.Loading)

            if (networkHelper.isNetworkConnected()) {
                try {
                    val movieResponse = remoteDataSource.getMovieDetails(movieId)

                    if (movieResponse.isSuccessful) {
                        movieResponse.body()?.let {
                            emit(DataState.Success(it))
                        }
                            ?: emit(DataState.Error("something went wrong, please check internet connection!"))
                    } else {
                        emit(DataState.Error("something went wrong, please check internet connection!"))
                    }
                } catch (e: Exception) {
                    emit(DataState.Error("something went wrong, please check internet connection!"))
                }
            } else {
                emit(DataState.Error("no internet connection!"))
            }
        }.flowOn(IOContext)
}