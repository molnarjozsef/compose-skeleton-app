package com.jozsefmolnar.newskeletonapp.model.api

import com.jozsefmolnar.newskeletonapp.model.data.FooDataModel
import kotlinx.serialization.Serializable

@Serializable
data class FooApiModel(
    val name: String,
) {
    fun toDataModel() = FooDataModel(
        name = name
    )
}
