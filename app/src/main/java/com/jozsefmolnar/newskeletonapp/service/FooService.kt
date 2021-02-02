package com.jozsefmolnar.newskeletonapp.service

import com.jozsefmolnar.newskeletonapp.BuildConfig
import com.jozsefmolnar.newskeletonapp.model.api.FooResponseApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface FooService {

    @GET("latest-foo")
    suspend fun getLatestFoo(
        @Query("API_KEY")
        apiKey: String = BuildConfig.FOO_API_KEY,
    ): FooResponseApiModel
}