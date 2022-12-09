package com.jozsefmolnar.newskeletonapp.model.api

@kotlinx.serialization.Serializable
data class NewsResponseApiModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleApiModel>,
)
