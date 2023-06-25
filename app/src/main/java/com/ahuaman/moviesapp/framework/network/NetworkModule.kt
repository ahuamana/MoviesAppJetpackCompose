package com.ahuaman.moviesapp.framework.network

import com.ahuaman.moviesapp.BuildConfig
import com.ahuaman.moviesapp.data.remote.MoviesService
import com.ahuaman.moviesapp.framework.network.internetconnection.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideOkHttpClient(connectivityInterceptor: ConnectivityInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            //.addInterceptor(connectivityInterceptor) // Not implemented yet //take care
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
         okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}