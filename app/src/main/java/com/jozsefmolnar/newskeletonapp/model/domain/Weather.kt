package com.jozsefmolnar.newskeletonapp.model.domain

import com.jozsefmolnar.newskeletonapp.model.data.WeatherDataModel

data class Weather(
    val temperature: Float,
    val description: String?,
) {
    fun mapToDataModel() =
        WeatherDataModel(
            temperature = temperature,
            description = description,
        )
}
