package com.jozsefmolnar.newskeletonapp.model.api

data class NewsResponseApiModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleApiModel>,
)
