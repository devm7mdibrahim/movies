package com.devm7mdibrahim.movies.di

import android.content.Context
import com.devm7mdibrahim.movies.data.remote.RemoteDataSourceImpl
import com.devm7mdibrahim.movies.BuildConfig
import com.devm7mdibrahim.movies.data.remote.ApiService
import com.devm7mdibrahim.movies.data.remote.RemoteDataSource
import com.devm7mdibrahim.movies.utils.BASE_URL
import com.devm7mdibrahim.movies.utils.NetworkHelper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideInterceptor(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val url = chain.request().url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }

        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context) = NetworkHelper(context)

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}