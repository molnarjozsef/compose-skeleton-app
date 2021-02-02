package com.jozsefmolnar.newskeletonapp.model.api

import com.squareup.moshi.Json

@Json(name = "Foo")
data class FooApiModel(
    val name: String
)