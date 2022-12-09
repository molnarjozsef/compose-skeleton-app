package com.jozsefmolnar.newskeletonapp.model.api

@kotlinx.serialization.Serializable
data class ArticleApiModel(
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val source: SourceApiModel?,
    val author: String?,
)
