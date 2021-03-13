package com.devm7mdibrahim.movies.di

import com.devm7mdibrahim.movies.data.remote.RemoteDataSource
import com.devm7mdibrahim.movies.data.repo.details.DetailsRepository
import com.devm7mdibrahim.movies.data.repo.details.DetailsRepositoryImpl
import com.devm7mdibrahim.movies.data.repo.main.MainRepository
import com.devm7mdibrahim.movies.data.repo.main.MainRepositoryImpl
import com.devm7mdibrahim.movies.utils.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun provideMainRepository(
        remoteDataSource: RemoteDataSource,
        networkHelper: NetworkHelper,
        coroutineContext: CoroutineContext
    ): MainRepository = MainRepositoryImpl(remoteDataSource, networkHelper, coroutineContext)

    @ViewModelScoped
    @Provides
    fun provideDetailsRepository(
        remoteDataSource: RemoteDataSource,
        networkHelper: NetworkHelper,
        coroutineContext: CoroutineContext
    ): DetailsRepository = DetailsRepositoryImpl(remoteDataSource, networkHelper, coroutineContext)
}