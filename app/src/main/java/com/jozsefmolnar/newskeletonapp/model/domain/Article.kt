package com.jozsefmolnar.newskeletonapp.model.domain

data class Article(
    val id: Int? = null,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val author: String?,
    val source: String?,
)
