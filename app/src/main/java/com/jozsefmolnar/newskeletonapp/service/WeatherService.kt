package com.jozsefmolnar.newskeletonapp.service

import com.jozsefmolnar.newskeletonapp.model.api.WeatherApiModel
import retrofit2.http.GET

interface WeatherService {

    @GET("forecast?latitude=47.4979&longitude=19.0402&current_weather=true")
    suspend fun getWeather(): WeatherApiModel
}
