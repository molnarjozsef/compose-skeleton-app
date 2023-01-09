package com.jozsefmolnar.newskeletonapp.service

import com.jozsefmolnar.newskeletonapp.model.api.WeatherApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast?current_weather=true")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherApiModel
}
