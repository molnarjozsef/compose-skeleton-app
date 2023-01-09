
package com.jozsefmolnar.newskeletonapp.model.api

import com.jozsefmolnar.newskeletonapp.model.api.constant.WeatherMap
import com.jozsefmolnar.newskeletonapp.model.domain.Weather
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class WeatherApiModel(
    @SerialName("current_weather")
    val currentWeather: CurrentWeather,
) {
    fun mapToDomainModel() =
        Weather(
            temperature = currentWeather.temperature,
            description = WeatherMap[currentWeather.weatherCode]
        )
}

@kotlinx.serialization.Serializable
data class CurrentWeather(
    val temperature: Float,
    @SerialName("weathercode")
    val weatherCode: Int,
)
