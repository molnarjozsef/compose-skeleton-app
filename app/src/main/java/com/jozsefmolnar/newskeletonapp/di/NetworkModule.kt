package com.jozsefmolnar.newskeletonapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jozsefmolnar.newskeletonapp.service.NewsService
import com.jozsefmolnar.newskeletonapp.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory = Json { ignoreUnknownKeys = true }
        .asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    @Named("NewsApi")
    fun provideNewsApiRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .baseUrl("https://newsapi.org/v2/")
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    @Named("WeatherApi")
    fun provideWeatherApiRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .baseUrl("https://api.open-meteo.com/v1/")
        .client(okHttpClient)
        .build()

    @Provides
    fun provideNewsApiService(@Named("NewsApi") retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)

    @Provides
    fun provideWeatherApiService(@Named("WeatherApi") retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}
