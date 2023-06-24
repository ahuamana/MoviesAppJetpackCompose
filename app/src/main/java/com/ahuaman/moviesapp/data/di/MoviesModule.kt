package com.ahuaman.moviesapp.data.di

import com.ahuaman.moviesapp.data.remote.IMoviesRemoteDataSource
import com.ahuaman.moviesapp.data.remote.IMoviesService
import com.ahuaman.moviesapp.data.remote.MoviesRemoteDataSource
import com.ahuaman.moviesapp.data.remote.MoviesService
import com.ahuaman.moviesapp.data.remote.MoviesServiceImpl
import com.ahuaman.moviesapp.data.repository.IMoviesRepository
import com.ahuaman.moviesapp.data.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesModule {

    @Singleton
    @Binds
    abstract fun provideMovieServices(
        moviesServiceImpl: MoviesServiceImpl
    ): IMoviesService

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSource: MoviesRemoteDataSource
    ): IMoviesRemoteDataSource

    @Singleton
    @Binds
    abstract fun provideMoviesRepository(
        moviesRepositoryImpl: MoviesRepository
    ): IMoviesRepository


}


@Module
@InstallIn(SingletonComponent::class)
object MoviesModuleObj {

    @Singleton
    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}

