package com.jozsefmolnar.newskeletonapp.model.api

data class FooResponseApiModel(
    val status: String,
    val totalResults: Int,
    val items: List<FooApiModel>,
)
