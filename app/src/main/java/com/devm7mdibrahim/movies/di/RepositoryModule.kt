package com.devm7mdibrahim.movies.di

import com.devm7mdibrahim.movies.data.remote.RemoteDataSource
import com.devm7mdibrahim.movies.data.remote.RemoteDataSourceImpl
import com.devm7mdibrahim.movies.data.repo.details.DetailsRepository
import com.devm7mdibrahim.movies.data.repo.details.DetailsRepositoryImpl
import com.devm7mdibrahim.movies.data.repo.main.MainRepository
import com.devm7mdibrahim.movies.data.repo.main.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    abstract fun provideDetailsRepository(detailsRepositoryImpl: DetailsRepositoryImpl): DetailsRepository

    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}