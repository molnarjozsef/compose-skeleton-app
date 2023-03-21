package com.jozsefmolnar.newskeletonapp.model.api

import kotlinx.serialization.Serializable

@Serializable
data class FooResponseApiModel(
    val items: List<FooApiModel>,
)
