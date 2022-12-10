package com.jozsefmolnar.newskeletonapp.service

import com.jozsefmolnar.newskeletonapp.BuildConfig
import com.jozsefmolnar.newskeletonapp.model.api.NewsResponseApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines")
    suspend fun getLatestNews(
        @Query("apiKey")
        apiKey: String = BuildConfig.NEWSAPI_KEY,
        @Query("country")
        countryCode: String
    ): NewsResponseApiModel
}
