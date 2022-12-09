package com.jozsefmolnar.newskeletonapp.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jozsefmolnar.newskeletonapp.service.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl() = "https://newsapi.org/v2/"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory = Json.asConverterFactory("application/json".toMediaType())

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        baseUrl: String,
        converterFactory: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(converterFactory)
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)
}
