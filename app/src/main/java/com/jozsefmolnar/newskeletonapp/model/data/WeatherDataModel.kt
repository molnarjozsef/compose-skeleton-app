package com.jozsefmolnar.newskeletonapp.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jozsefmolnar.newskeletonapp.model.domain.Weather

@Entity(tableName = "weather")
data class WeatherDataModel(
    @PrimaryKey
    var id: Int = 0,
    val temperature: Float,
    val description: String?,
) {
    fun mapToDomainModel() =
        Weather(
            temperature = temperature,
            description = description,
        )
}
