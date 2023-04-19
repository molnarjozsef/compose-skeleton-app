package com.jozsefmolnar.newskeletonapp.service

import com.jozsefmolnar.newskeletonapp.model.api.FooResponseApiModel
import retrofit2.http.GET

interface FooService {

    @GET("latest-foo")
    suspend fun getLatestFoo(): FooResponseApiModel
}
