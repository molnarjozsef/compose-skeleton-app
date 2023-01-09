package com.jozsefmolnar.newskeletonapp.model.api

import com.jozsefmolnar.newskeletonapp.model.domain.Article

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
) {
    fun mapToDomainModel() = Article(
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        author = author?.takeIf { it.isNotBlank() },
        source = source?.name?.takeIf { it.isNotBlank() }
    )
}
